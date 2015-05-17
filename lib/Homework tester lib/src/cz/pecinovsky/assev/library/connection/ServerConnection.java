/**
 * Připojení na server
 */
package cz.pecinovsky.assev.library.connection;

import cz.pecinovsky.assev.library.AssignmentDescriptor;
import cz.pecinovsky.assev.library.SolutionDescriptor;
import cz.pecinovsky.assev.library.gui.Gui;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import cz.pecinovsky.assev.library.ui.Evidence;

/**
 * Třída soužící jako spojení na server
 *
 * @author Petr Kokeš
 * @version 2.00
 */
public class ServerConnection  {

    private Socket socket;
    public BufferedReader input;
    private PrintWriter output;
    private Thread t;
    private Evidence evidence;
    private boolean libraryMode;
    public boolean initFailed = false;

    /**
     * Konstruktor třídy pro připojení na server dle parametrů z configu hráče.
     *
     * @param evidence evidence
     */
    public ServerConnection(Evidence evidence, boolean libraryMode) {
        initFailed = false;
        this.libraryMode = libraryMode;
        this.evidence = evidence;
        try {
            socket = new Socket("localhost", Integer.parseInt("6666"));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            System.out.println("input created");
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            System.out.println("output created");
            if (this.libraryMode) {
                sendMessage("login-tester");
                t = new Thread(new ActionChecker(), "server-client-connection");
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Server not running !!!");
            initFailed = true;
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Uzavírání připojení při skončení hry
     *
     * @throws IOException chyba
     */
    public boolean close(){
        try {
            if (libraryMode) {
                t.destroy();
            }
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
            return true;
        } catch (IOException ex) {
            return false;           
        }
    }

    /**
     * Nepovedená testovací funkce pro treat jako oddělenou třídu
     */
     public class ActionChecker implements Runnable {

    public void run() {
        boolean notAll = true;
        while (notAll) {
            try {
                String odpoved = input.readLine();
//                System.out.println(odpoved);
                String[] contents = odpoved.split("-");
                if (contents[0].matches("homework")) {
                    SolutionDescriptor solution = new SolutionDescriptor(contents[1], contents[2], contents[3], contents[4], contents[5], contents[6]);
                    evidence.solutions.add(solution);
                    System.out.println("Added new solution id: " + contents[1] + " !");
//                    System.out.println("Total solutions to test: " + evidence.solutions.size() + " !\n");
                }else if(odpoved.matches("array-end")){
                    evidence.transmiting = false;
            }else if(contents[0].matches("assignment")){
                    AssignmentDescriptor assignment = new AssignmentDescriptor(contents[1], contents[2], contents[3], contents[4], contents[5]);
                    evidence.assignment.add(assignment);
                    System.out.println("Added new assignment id: " + contents[1] + " !");
            }else if(odpoved.matches("solution-ok")){
                    evidence.solutionScoreTransmiting = false;
                    evidence.solutionScore = true;
            }

                //System.out.println(odpoved);
            } catch (IOException ex) {
                System.out.println("Server error!!!");
            }
            try {
                t.sleep(100);
            } catch (InterruptedException ex) {

            }
        }
    }
    }

    /**
     * Funkce pro poslání hlášky o stavu na server
     *
     * @param message sring správa
     * @throws IOException chyba
     */
    public void sendMessage(String message) {
        output.println(message);
    }

}
