package ui;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import logika.ApiClient;
import logika.Evidence;
import logika.FtpClient;
import logika.PraceSDaty;

/**
 * Třída pro stažení dat z DB
 * @author Petr Kokeš
 */
public class OknoDownload {

    private JDialog oknoVytvorPdf;
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
    private ApiClient apiClient;
    
/**
 * Konstruktor třídy
 * @param evidence
 * @param archiv
 * @param ftpClient
 * @throws Exception 
 */
    public OknoDownload(Evidence evidence, PraceSDaty archiv, FtpClient ftpClient) throws Exception {
        this.archiv = archiv;
        this.evidence = evidence;
        this.ftpClient = ftpClient;
        this.apiClient = new ApiClient(evidence);  
        init();

    }

    /**
     * Vnitřní třída implementující ActionListener slouží k zavření okna
     * k akci
     */
    public class Zpet implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent udalost) {
            oknoVytvorPdf.setVisible(false);
        }
    }
    /**
     * Vnitřní třída implementující ActionListener slouží k zahájení akce
     */
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

    /**
     * Metoda vytváří okno "Synchronizace ze serveru" a jeho komponenty
     */
    private void init() {

        oknoVytvorPdf = new JDialog();
        oknoVytvorPdf.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        oknoVytvorPdf.setTitle("Synchronizace ze serveru");
        oknoVytvorPdf.setLocation(400, 350);
        oknoVytvorPdf.setSize(400, 200);
        oknoVytvorPdf.setLayout(null);

        krokLabel = new JLabel("Stáhnutí web dat");
        Font pismo = new Font("Arial", Font.BOLD, 14);
        krokLabel.setFont(pismo);
        oknoVytvorPdf.add(krokLabel);
        krokLabel.setBounds(90, 10, 250, 20);


        startButton = new JButton("Start");
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


        oknoVytvorPdf.add(startButton);
        oknoVytvorPdf.add(progressBar);


        vybratButton = new JButton("Zpět");
        oknoVytvorPdf.add(vybratButton);
        vybratButton.setBounds(20, 110, 120, 25);
        vybratButton.addActionListener(new Zpet());
    }

    /**
     * Zajišťuje zobrazení nebo skrytí okna.
     *
     * @param viditelnost
     */
    public void setVisible(boolean viditelnost) {
        oknoVytvorPdf.setVisible(viditelnost);
    }
/**
 * Vnitřní třída která stáhne soubory na pozadí s možností sledování procentuálního postupu
 */
    class Task extends SwingWorker<Void, Void> {
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
}