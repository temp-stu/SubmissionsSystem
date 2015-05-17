package ui;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import logika.Evidence;
import logika.FtpClient;
import logika.PraceSDaty;
import model.*;

/**
 * Třída pro stažení všech souborů k úkolům a publikacím do počítače
 * @author Petr Kokeš
 */
public class OknoDownloadZFtp {
    
    public JDialog downloadOkno;
    private JLabel krokLabel;
    private JButton zpetButton;
    public PraceSDaty archiv;
    public Evidence evidence;
    private FtpClient ftpClient;
    public int sablona;
    private JProgressBar progressBar;
    private JButton startButton;
    private JTextArea taskOutput;
    private Task task;
    private OknoDownloadZFtp oknoDownloadZFtp;

/**
 * Konstruktor třídy vytvářející lokální instance
 * @param evidence
 * @param praceSDaty
 * @param ftpClient
 * @throws Exception 
 */
    public OknoDownloadZFtp(Evidence evidence, PraceSDaty praceSDaty, FtpClient ftpClient) throws Exception {
        oknoDownloadZFtp = this;
        this.evidence = evidence;
        this.archiv = praceSDaty;
        this.ftpClient = ftpClient;
        init();

    }
    
/**
 * třída pro zavření okna
 */
    public class Zpet implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent udalost) {
            downloadOkno.setVisible(false);
        }
    }
/**
 * třída pro zahájení aktualizace
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
/**
 * Třída pro změnu progessbaru
 */
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
     * Metoda vytváří okno  a jeho komponenty
     */
    private void init() {

        downloadOkno = new JDialog();
        downloadOkno.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        downloadOkno.setTitle("Stahování souborů");
        downloadOkno.setLocation(400, 350);
        downloadOkno.setSize(400, 200);
        downloadOkno.setLayout(null);

        krokLabel = new JLabel("Stahování všech souborů z FTP");
        Font pismo = new Font("Arial", Font.BOLD, 14);
        krokLabel.setFont(pismo);
        downloadOkno.add(krokLabel);
        krokLabel.setBounds(80, 10, 250, 20);


        startButton = new JButton("Stáhnout");
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


        downloadOkno.add(startButton);
        downloadOkno.add(progressBar);
        
        downloadOkno.setVisible(true);

        zpetButton = new JButton("Zpět");
        downloadOkno.add(zpetButton);
        zpetButton.setBounds(20, 110, 120, 25);
        zpetButton.addActionListener(new Zpet());
    }

    /**
     * Zajišťuje zobrazení nebo skrytí okna.
     *
     * @param viditelnost
     */
    public void setVisible(boolean viditelnost) {
        downloadOkno.setVisible(viditelnost);
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */


        @Override
        public Void doInBackground() throws Exception {
               Random random = new Random();
            setProgress(0);


            try {
                    Thread.sleep(1000 + random.nextInt(2000));
                } catch (InterruptedException ignore) {
                }
            setProgress(30);
            ftpClient.download();
            
//            ftpClient.vytvorHlavniSoubory();
            
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