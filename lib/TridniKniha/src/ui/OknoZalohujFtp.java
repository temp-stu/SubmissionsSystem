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
import logika.Evidence;
import logika.FtpClient;
import logika.PraceSDaty;

/**
 * Předpřipravvené třída pro nahrávání publikací
 * @author Petr Kokeš
 */
public class OknoZalohujFtp {

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
    
/**
 * Kontroktor třídy vytvářející instance na třídy
 * @param evidence
 * @param archiv
 * @param ftpClient 
 */
    public OknoZalohujFtp(Evidence evidence, PraceSDaty archiv, FtpClient ftpClient) {
        this.archiv = archiv;
        this.evidence = evidence;
        this.ftpClient = ftpClient;
        
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
     * k akci
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
     * Vnitřní třída implementující PropertyChangeListener slouží k změně progessbaru
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
     * Metoda vytváří okno "Zálohování souborů" a jeho komponenty
     */
    private void init() {

        oknoVytvorPdf = new JDialog();
        oknoVytvorPdf.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        oknoVytvorPdf.setTitle("Zálohování souborů");
        oknoVytvorPdf.setLocation(400, 350);
        oknoVytvorPdf.setSize(400, 200);
        oknoVytvorPdf.setLayout(null);

        krokLabel = new JLabel("Zálohuj vše na FTP");
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
 * Vnitřní třída sloužící k provedení akcí s použítí progessbaru
 */
    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */

        @Override
        public Void doInBackground() throws Exception {

            int progress = 15;
            setProgress(0);
            
          
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