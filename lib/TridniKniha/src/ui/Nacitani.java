/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import logika.*;
import model.*;

/**
 *
 * @author xkokp00
 */
public class Nacitani {

    public JFrame NacitaciOkno;
    private JLabel krokLabel;
    private JButton vybratButton;
    public PraceSDaty archiv;
    public Evidence evidence;
    private FtpClient ftpClient;
    public int sablona;
    private JProgressBar progressBar;
    private JButton startButton;
    private JTextArea taskOutput;
    private Task task;
    private Nacitani nacitani;
    private ApiClient apiClient;
    public String localPath;
    private String configPath;


    public Nacitani() throws Exception {        
        nacitani = this;
        initHeader();
        localPath();

    }
    
    private void localPath() throws Exception{
    localPath = getLocalPath();
    if(localPath.equals("1")){
    instal();
    }else{
    this.evidence = new Evidence(localPath);
        this.archiv = new PraceSDaty(this.evidence);
        this.ftpClient = new FtpClient(evidence);
        this.apiClient = new ApiClient(evidence);
            init();
    }
    }
    
    /**
     * Funkce pro prvotní spuštění aplikace, která vytvoří potřebné složky
     */
public void instal(){
        try {
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            j.setDialogTitle("Vyberte umístění instalační složky");
            Integer opt = j.showSaveDialog(NacitaciOkno);
            
            localPath = j.getSelectedFile().getPath();
            System.out.println("Localpath: "+localPath);
            saveLocalPath(localPath);
           
            
            
            String strAplikace = localPath+"\\web\\app\\";
            String strHlavniSoubory = localPath+"\\web\\data\\main\\";
            String strDownloadSoubory = localPath+"\\web\\data\\FTP\\download";
            String strUplouadSoubory = localPath+"\\web\\data\\FTP\\upload";
            String strAktualizace = localPath+"\\web\\data\\FTP\\aktualizace";

            // Create multiple directories
            boolean successAplikace = (new File(strAplikace)).mkdirs();
            boolean successData = (new File(strHlavniSoubory)).mkdirs();
            boolean successDownload = (new File(strDownloadSoubory)).mkdirs();
            boolean successUpload = (new File(strUplouadSoubory)).mkdirs();
            boolean successAktualizace = (new File(strAktualizace)).mkdirs();

//            if (successAplikace || successData || successDownload || successUpload || successAktualizace) {
                System.out.println("Directories: "
                        + successAplikace + successData + successDownload + successUpload + successAktualizace + " created");
            //}
                
    this.evidence = new Evidence(localPath);
        this.archiv = new PraceSDaty(this.evidence);
        this.ftpClient = new FtpClient(evidence);
        this.apiClient = new ApiClient(evidence);
        init();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
}

    /**
     * Vnitřní třída implementující ActionListener slouží k přihlášení studenta
     * k akci
     */
    public class Zpet implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent udalost) {
            try {
              Evidence evidence = new Evidence(localPath);
              TableModel modelVipFirmy = new ModelStudenti(evidence);
              TableModel modelNeplaticiFirmy = new ModelStatickeStranky(evidence);
              TableModel modelVsechnyFirmy = new ModelOdevzdaneUkoly(evidence);
              TableModel modelPlaticiFirmy = new ModelPublikace(evidence);
              TableModel modelVipPlatFirmy = new ModelUkoly(evidence);
              PraceSDaty archiv = evidence.getArchiv();
              Gui gui = new Gui(modelVipFirmy, modelNeplaticiFirmy, modelVsechnyFirmy, modelPlaticiFirmy,modelVipPlatFirmy, archiv, evidence, ftpClient,  nacitani);
            } catch (Exception ex) {
                Logger.getLogger(Nacitani.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class Start implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            progressBar.setIndeterminate(true);
            startButton.setEnabled(false);
            task = new Task();
            task.addPropertyChangeListener(new Change());
            task.execute();
        }
    }

    public class Change implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            if ("progress" == evt.getPropertyName()) {
                int progress = (Integer) evt.getNewValue();
                progressBar.setIndeterminate(false);
                progressBar.setValue(progress);
                taskOutput.append(String.format(
                        "Completed %d%% of task.\n", progress));
            }
        }
    }
    
    private void initHeader(){
    NacitaciOkno = new JFrame();
        NacitaciOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NacitaciOkno.setTitle("Synchronizace dat");
        NacitaciOkno.setLocation(400, 350);
        NacitaciOkno.setSize(400, 200);
        NacitaciOkno.setLayout(null);
    }
    

    /**
     * Inicializace základních komponent okna
     */
    private void init() {

        

        krokLabel = new JLabel("Synchronizace s web aplikací");
        Font pismo = new Font("Arial", Font.BOLD, 14);
        krokLabel.setFont(pismo);
        NacitaciOkno.add(krokLabel);
        krokLabel.setBounds(90, 10, 250, 20);


        startButton = new JButton("Načíst");
        startButton.setActionCommand("start");
        startButton.setBounds(250, 110, 120, 25);
        startButton.addActionListener(new Start());

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(20, 60, 350, 25);
        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5, 5, 5, 5));
        taskOutput.setEditable(false);


        NacitaciOkno.add(startButton);
        NacitaciOkno.add(progressBar);
        
        NacitaciOkno.setVisible(true);

        vybratButton = new JButton("Otevřít");
        NacitaciOkno.add(vybratButton);
        vybratButton.setBounds(20, 110, 120, 25);
        vybratButton.addActionListener(new Zpet());
    }

    /**
     * Zajišťuje zobrazení nebo skrytí okna.
     *
     * @param viditelnost
     */
    public void setVisible(boolean viditelnost) {
        NacitaciOkno.setVisible(viditelnost);
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */


        @Override
        public Void doInBackground() throws Exception {

            setProgress(0);

            try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                }
            setProgress(10);
            
            ftpClient.download();

            setProgress(30);
            
            apiClient.downloadFile("admin/api/students","studenti.csv");
            
            setProgress(40);
            
            apiClient.downloadFile("admin/api/static/1","menu.csv");
            setProgress(48);
            
            apiClient.downloadFile("admin/api/statickat","kategoriemenu.csv");
            setProgress(54);
            
            apiClient.downloadFile("admin/api/publikace/1","publikace.csv");
            setProgress(61);
                    
            apiClient.downloadFile("admin/api/publikacekat","publikacekategorie.csv");
            setProgress(70);
            
            apiClient.downloadFile("admin/api/skoly","skoly.csv");
            setProgress(75);
            
            apiClient.downloadFile("admin/api/fakulty","fakulty.csv");
            setProgress(79);
            
            apiClient.downloadFile("admin/api/skupiny","skupinysemestrpredmet.csv");
            setProgress(85);
            
            apiClient.downloadFile("admin/api/predmety","predmet.csv");
            setProgress(90);
            
            apiClient.downloadFile("admin/api/ukoly/1","ukoly.csv");

            setProgress(95);
            apiClient.downloadFile("admin/api/userukoly/1","userukoly.csv");

            setProgress(100);            
            
        Evidence evidence = new Evidence(localPath);
        TableModel modelStudenti = new ModelStudenti(evidence);
        TableModel modelStatickeStranky = new ModelStatickeStranky(evidence);
        TableModel modelOdevzdaneUkoly = new ModelOdevzdaneUkoly(evidence);
        TableModel modelPublikace = new ModelPublikace(evidence);
        TableModel modelUkoly = new ModelUkoly(evidence);
        PraceSDaty archiv = evidence.getArchiv();
        Gui gui = new Gui(modelStudenti, modelStatickeStranky, modelOdevzdaneUkoly, modelPublikace,modelUkoly, archiv, evidence, ftpClient,  nacitani);

            return null;
        }

        /*
         * Executed in event dispatch thread
         */
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            startButton.setEnabled(true);
            taskOutput.append("Done!\n");
        }
    }
    
        public String getLocalPath() {
        String content = "";
        String userName = System.getProperty("user.name");
        //generování aktivní složky a a složek pro zápis PDF souborů
        //System.out.println(System.getProperty("os.name").startsWith("Windows"));
        if(System.getProperty("os.name").startsWith("Windows")==true){
            configPath = "C:/Users/" + userName + "/AppData/Roaming/WebUcitele/";
        }else{
            configPath = "/Users/" + userName + "/Library/Application Support/WebUcitele/";
        }

            File f = new File(configPath+"config.txt");
            if(f.exists()){
                System.out.println("Config file found!");
            try { 
                content = new Scanner(f).useDelimiter("\\Z").next();
            } catch (Exception ex) {
                //Logger.getLogger(PraceSDaty.class.getName()).log(Level.SEVERE, null, ex);
                content = "1";
            }
            System.out.println("Local path: "+content);
            }else{
                System.out.println("Config file don't exist!");
                boolean success = (new File(configPath)).mkdirs();
            if (success) {
                    try {
                        f.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(PraceSDaty.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.out.println("Directories: "  + configPath + " created");
                content = "1";
            }
            }
             //Create multiple directories

    return content;}
        
       private void saveLocalPath(String localPath){
           File f = new File(configPath+"config.txt");
           BufferedWriter logger = null;
        try {
            logger = new BufferedWriter(new FileWriter(f, true));
            logger.write(localPath);

        } catch (IOException e) {
            System.out.println("Soubor nebyl nalezen.");
        } finally {
            if (logger != null) {
                try {
                    logger.close();
                } catch (IOException ex) {
                    System.out.println("Chyba v uzavirani souboru");
                }
            }
        }
       }
}