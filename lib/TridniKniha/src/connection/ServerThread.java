package connection;

import session.GameManager;
import session.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import logika.Evidence;
import org.apache.log4j.Logger;

/**
 * Instance této třídy slouží ke komunikaci s klientem, tvoří nové vlákno, které
 * je souštěno třídou Server.
 *
 * Pro každéo připojeného klinta je vytvořeno nové vlákno.
 *
 *
 * @author Bc. Petr Kokeš
 */
public class ServerThread extends Thread {

    private static final Logger LOG = Logger.getLogger(ServerThread.class);

    /**
     * Socket patřící vláknu (klientu)
     */
    private final Socket socket;

    /**
     * Manažer hry
     */
    private final GameManager gameManager;

    /**
     * Vstup = klientův výstup
     */
    private BufferedReader input;

    /**
     * Výstup = klientův vstup
     */
    private PrintWriter output;

    /**
     * Klientova IP adresa
     */
    InetAddress clientIp;

    /**
     * Hráč, kterému patří toto vlákno
     */
    Player player;

    /**
     * Vytváří nové vlákno (pro každého klienta)
     *
     * @param socket Klientův socket, pro který je vlákno vytvářeno
     * @param gameManager Instance manažera hry
     */
    public ServerThread(Socket socket, GameManager gameManager) {
        super("ServerThread");
        this.socket = socket;
        this.gameManager = gameManager;
    }

    /**
     * Spustí vlákno pro každého připojenáho klienta. Kódování je UTF-8.
     *
     */
    @Override
    public void run() {
        try {
            clientIp = socket.getInetAddress();
            //System.out.println("[T] Created new thread for client, IP: " + clientIp);
            LOG.info("Client has connected: " + clientIp);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            player = new Player(output, input);

            output.println("HELLO");//uvítací příkaz, posílán klientovi při úspěšném navázání spojení

            String line; //přijatá zpráva
            do {
                line = input.readLine();
                LOG.debug("Received " + line + " from IP " + clientIp);

                if (!(line == null || line.equals("clear") || line.equals("game-end"))) {
                    //zpráva není prázdná a neobsahuje interní příkaz "clear"
                    //taky necheme předat game-end, protože místo něj pošleme clear
                    gameManager.resolve(line, player);
                }
            } while (line != null && !line.equals("game-end"));

            output.println("BYE");//ukončovací příkaz, poslán klientovi před ukončením připojení
        } catch (IOException e) {
            LOG.error("Error in the run() block: \n" + e);
        } finally {
            if (player != null) {//pokud nebyl hráč inicializován, není koho vyhazovat
                gameManager.resolve("clear", player); //řekne gameManageru, že má tohoto hráče vyhodit z místnosti
            }
            endConnection();
        }
    }

    /**
     * Doufám, že korektně uzavře spojení s klientem
     */
    public void endConnection() {
        try {
            LOG.debug("Trying to end connection with client "
                    + clientIp);
            output.flush();
            socket.close();
            LOG.info("Client has left: " + clientIp);
        } catch (IOException ex) {
            LOG.error("Unable to close connection with client "
                    + clientIp, ex);

        }
    }
}
