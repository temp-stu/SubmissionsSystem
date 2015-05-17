/**
 * Třída pro stahování a synchronizace CSV souboru z API webu
 */
package logika;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.Nacitani;

/**
 *
 * @author Petr Kokeš
  * @version 2.00
 */
public class ApiClient {

    public String server = "cool-it-1.cz";
    public String username = "admin";
    public String password = "madPC2014";
    private Nacitani nacitani;
    private Evidence evidence;

/**
 * Konstruktor třídy pro nastavení servru
 * @param evidence
 * @throws Exception 
 */
    public ApiClient(Evidence evidence) throws Exception {
        this.nacitani = nacitani;
        this.evidence = evidence;

        server = evidence.ftpLoginData.get(1).getServer();
        username = evidence.ftpLoginData.get(1).getUser();
        password = evidence.ftpLoginData.get(1).getPass();

    }
/**
 * Při změně servveru je nutné aktualizovat udaje
 */
    public void zmena() {
        server = evidence.ftpLoginData.get(1).getServer();
        username = evidence.ftpLoginData.get(1).getUser();
        password = evidence.ftpLoginData.get(1).getPass();
    }
/**
 * Stažení souborů
 * @param urlpath api funkce
 * @param name název souboru
 * @throws IOException 
 */
    public void downloadFile(String urlpath, String name) throws IOException {
        System.out.println("opening connection");
        URL url = new URL("http://" + server +"/"+ urlpath);
        InputStream in = url.openStream();
        FileOutputStream fos = new FileOutputStream(new File(evidence.localPath+"\\web\\data\\main\\"+name));

        System.out.println("reading file...");
        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from
        // connection
        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
        System.out.println("file "+name+" was downloaded");
    }  
    /**
     * Funkce na synchronizace dat na server
 * @param urlpath api funkce
 * @param name název souboru
     * @throws IOException 
     */
    public void uploadFile(String urlpath, String name) throws IOException {
try {
    String urlToConnect = "http://" + server +"/"+ urlpath;
    File fileToUpload = new File(evidence.localPath+"\\web\\data\\main\\"+name);
    String boundary = Long.toHexString(System.currentTimeMillis()); 
    System.out.println("builing url");
    URLConnection connection = new URL(urlToConnect).openConnection();
    connection.setDoOutput(true); 
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    PrintWriter writer = null;
    try {
        writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.println("--" + boundary);
        writer.println("Content-Disposition: form-data; name=\"file\"; filename=\""+name+"\"");
        writer.println("Content-Type: text/csv");
        writer.println();
        BufferedReader reader = null;
        System.out.println("Seting file: "+name);
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToUpload)));
            for (String line; (line = reader.readLine()) != null;) {
                writer.println(line);
            }
        } finally {
            if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
        }
        writer.println("--" + boundary + "--");
    } finally {
        if (writer != null) writer.close();
    }
    int responseCode = ((HttpURLConnection) connection).getResponseCode();
    System.out.println(responseCode); // Should be 200
    System.out.println("Sended ");
}   catch (IOException ex) {
            Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
