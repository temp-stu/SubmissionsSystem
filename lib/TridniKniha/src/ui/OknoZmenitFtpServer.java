
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import logika.*;

/**
 * Třída vytvářející okno pro změnu FTP servru
 * @author Petr Kokeš
 */
public class OknoZmenitFtpServer {
public JDialog oknoFtpLoginDialog;
    private JLabel serverLabel;
    private JLabel UserNameLabel;
    private JLabel PasswordLabel;
    private JLabel ProtokolLabel;
    private JTextField serverTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField protokolTextField;
    private JButton okButton;
    public Evidence evidence;
    public logika.PraceSDaty praceSDaty;
    private FtpClient ftpClient;
    public Gui gui;
    private int mode;
    private ApiClient apiClient;
    private File file;

    /**
     * Konstruktor vytvářející okno s formulářem na editaci
     */
    public OknoZmenitFtpServer(Evidence evidence, PraceSDaty praceSDaty, FtpClient ftpClient,ApiClient apiClient,File file, int mode) {
        this.evidence = evidence;
        this.apiClient = apiClient;
        this.file = file;
        this.mode = mode;
        this.praceSDaty = praceSDaty;
        this.ftpClient = ftpClient;
        init();
    }

    /**
     * Vnitřní třída implementující ActionListener slouží k editaci servru
     */
    private class PridaniAkce implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent udalost) {
            if (serverTextField.getText().equals("") || usernameTextField.getText().equals("") || passwordTextField.getText().equals("")
                    || protokolTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Všechna políčka musí být vyplněna!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            evidence.ftpLoginData.remove(mode);
            FtpLoginData ftpLoginData = new FtpLoginData(serverTextField.getText(), usernameTextField.getText(), 
                                          passwordTextField.getText(), protokolTextField.getText());
            evidence.ftpLoginData.add(mode,ftpLoginData);

            ftpClient.zmenaFtp();
            apiClient.zmena();
            praceSDaty.delete(file);
            String ftpConnection = evidence.ftpLoginData.get(0).getServer()+";"+evidence.ftpLoginData.get(0).getUser()+";"+evidence.ftpLoginData.get(0).getPass()+";"+evidence.ftpLoginData.get(0).getProtokol();
            String apiConnection = evidence.ftpLoginData.get(1).getServer()+";"+evidence.ftpLoginData.get(1).getUser()+";"+evidence.ftpLoginData.get(1).getPass()+";"+evidence.ftpLoginData.get(1).getProtokol();
            praceSDaty.ulozeniFtpLoginData(file,ftpConnection);
            praceSDaty.ulozeniFtpLoginData(file,apiConnection);
            oknoFtpLoginDialog.setVisible(false); 
            

        }
    }

    /**
     * Metoda vytváří okno Změna servrů s jednotlivými komponentami
     */
    private void init() {
        oknoFtpLoginDialog = new JDialog();
        oknoFtpLoginDialog.setTitle("Změna servrů");
        oknoFtpLoginDialog.setSize(400, 200);
        oknoFtpLoginDialog.setLocation(400, 300);
        oknoFtpLoginDialog.setLayout(null);
        oknoFtpLoginDialog.setVisible(true);
        oknoFtpLoginDialog.setResizable(false);

        initLabels();
        initTextField();

        okButton = new JButton("OK");
        oknoFtpLoginDialog.add(okButton);
        okButton.setBounds(255, 130, 100, 30);
        okButton.addActionListener(new PridaniAkce());
    }

    /**
     * Metoda vytváří popisky a umísťuje nich do okna
     */
    private void initLabels() {

        serverLabel = new JLabel("Servr:");
        oknoFtpLoginDialog.add(serverLabel);
        serverLabel.setBounds(25, 25, 150, 15);

        UserNameLabel = new JLabel("Uživatelské jméno");
        oknoFtpLoginDialog.add(UserNameLabel);
        UserNameLabel.setBounds(25, 50, 150, 15);

        PasswordLabel = new JLabel("Heslo:");
        oknoFtpLoginDialog.add(PasswordLabel);
        PasswordLabel.setBounds(25, 75, 150, 15);

        ProtokolLabel = new JLabel("Protokol (TLS / SSL):");
        oknoFtpLoginDialog.add(ProtokolLabel);
        ProtokolLabel.setBounds(25, 100, 150, 15);

       }

    /**
     * Metoda vytváří textové oblasti a umísťuje nich do okna
     */
    private void initTextField() {

        serverTextField = new JTextField();
        serverTextField.setText(evidence.ftpLoginData.get(mode).getServer());
        oknoFtpLoginDialog.add(serverTextField);
        serverTextField.setBounds(150, 22, 200, 20);

        usernameTextField = new JTextField();
        usernameTextField.setText(evidence.ftpLoginData.get(mode).getUser());
        oknoFtpLoginDialog.add(usernameTextField);
        usernameTextField.setBounds(150, 47, 200, 20);

        passwordTextField = new JTextField();
        passwordTextField.setText(evidence.ftpLoginData.get(mode).getPass());
        oknoFtpLoginDialog.add(passwordTextField);
        passwordTextField.setBounds(150, 72, 200, 20);

        protokolTextField = new JTextField();
        protokolTextField.setText(evidence.ftpLoginData.get(mode).getProtokol());
        oknoFtpLoginDialog.add(protokolTextField);
        protokolTextField.setBounds(150, 96, 200, 20);

        }

    /**
     * Metoda na zviditelnení okna
     *     
     * @param viditelnost
     */
    public void setVisible(boolean viditelnost) {
        oknoFtpLoginDialog.setVisible(viditelnost);
    }
}