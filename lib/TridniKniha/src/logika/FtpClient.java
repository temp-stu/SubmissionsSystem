package logika;

import java.io.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ui.Nacitani;

/**
 *Tato třída se stará a stahování dat z FTP serveru
 * @author Petr Kokeš
 */
public class FtpClient {

    public String server = "55455.w55.wedos.net";
    public String username = "w55455_webUcitele";
    public String password = "3AgjWSBL";
    public String remoteFile = "robots.txt";
    public String localFile = "C:/LetterGenerator/data/FTP/download/";
    public String remoteFileToTransfer = "robots.txt";
    public String localFileToTransfer = "robots.txt";
    public String protocol = "TLS"; // TLS / SSL
    public int port = 9030;
    public int odpoved;
    public boolean aktualizace = false;
    public int timeoutInMillis = 3000;
    public boolean isImpicit = false;
    private Nacitani nacitani;
    FTPClient client = new FTPSClient();
    public List<String> souboryKUp;
    private Evidence evidence;

/**
 * Metoda konstruktoru pro načtení údajů
 * @param evidence
 * @throws Exception 
 */
    public FtpClient(Evidence evidence) throws Exception {
        this.nacitani = nacitani;
        this.evidence = evidence;

        souboryKUp = new ArrayList<>();
        server = evidence.ftpLoginData.get(0).getServer();
        username = evidence.ftpLoginData.get(0).getUser();
        password = evidence.ftpLoginData.get(0).getPass();
        protocol = evidence.ftpLoginData.get(0).getProtokol();

    }
/**
 * Při změně FTP aktualizace údajů
 */
    public void zmenaFtp() {
        server = evidence.ftpLoginData.get(0).getServer();
        username = evidence.ftpLoginData.get(0).getUser();
        password = evidence.ftpLoginData.get(0).getPass();
        protocol = evidence.ftpLoginData.get(0).getProtokol();
    }
/**
 * Stažení nastartování FTP klienta
 * @throws IOException 
 */
    public void download() throws IOException {
        int pocetKroku = 0;
        System.setProperty("javax.net.debug", "TLS");

        client.setDataTimeout(timeoutInMillis);
        client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        try {
            int reply;

            client.connect(server, port);

            client.login(username, password);
            client.setFileType(FTP.BINARY_FILE_TYPE);

            client.enterLocalPassiveMode();

            System.out.println("Connected to " + server + ".");
            odpoved = client.sendCommand("CWD /www/files");
            System.out.println(odpoved);
            reply = client.getReplyCode();
            String remoteDirPath = "/www/files";
            String saveDirPath = evidence.localPath.replace("\\", "/")+"/web/data/FTP/download";
            System.out.println(saveDirPath);
            downloadDirectory(client, remoteDirPath, "", saveDirPath);

            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }

        } catch (Exception e) {

            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Could not connect to server.");
            JOptionPane.showMessageDialog(null,
                    "Vypnutý server nebo špaťně nastavené Login informace k serveru!!",
                    "ERROR!",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            System.out.println("# client disconnected");
            client.disconnect();
        }
    }

    /**
     * Funkce načte všechny složky a postupně našte vekeré soubory
     * @param ftpClient aktivní ftp připojení
     * @param parentDir složka ke stáhnutí
     * @param currentDir omezení výběru
     * @param saveDir kam se mají soubory uložit
     * @throws IOException 
     */
    public static void downloadDirectory(FTPClient ftpClient, String parentDir,
            String currentDir, String saveDir) throws IOException {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        FTPFile[] subFiles = ftpClient.listFiles(dirToList);

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath;
                String newDirPath;
                if (! currentDir.isEmpty()) {
                    filePath = parentDir + "/"+currentDir+"/" + currentFileName;
                    newDirPath = saveDir + parentDir + File.separator
                        + currentDir + File.separator + currentFileName;
                } else {
                    filePath = parentDir + "/" + currentFileName;
                    newDirPath = saveDir + parentDir + File.separator
                        + currentFileName;
                }

                if (aFile.isDirectory()) {
                    // create the directory in saveDir
                    File newDir = new File(newDirPath);
                    boolean created = newDir.mkdirs();
                    if (created) {
                        System.out.println("CREATED the directory: " + newDirPath);
                    } else {
                        System.out.println("COULD NOT create the directory: " + newDirPath);
                    }

                    // download the sub directory
                    downloadDirectory(ftpClient, dirToList, currentFileName,
                            saveDir);
                } else {
                    // download the file
                    boolean success = downloadSingleFile(ftpClient, filePath,
                            newDirPath);
                    if (success) {
                        System.out.println("DOWNLOADED the file: " + filePath);
                    } else {
                        System.out.println("COULD NOT download the file: "
                                + filePath);
                    }
                }
            }
        }
    }

    /**
     * Uložení konkrétního souboru
     * @param ftpClient aktivní FTP připojení
     * @param remoteFilePath vzálený soubor
     * @param savePath cesta kam se ma uložit
     * @return boolean uloženo či ne
     * @throws IOException 
     */
    public static boolean downloadSingleFile(FTPClient ftpClient,
            String remoteFilePath, String savePath) throws IOException {
        File downloadFile = new File(savePath);

        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (OutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(downloadFile))) {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
