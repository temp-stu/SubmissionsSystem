package ui;

import connection.ServerThread;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logika.*;
import session.GameManager;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

/**
 * Třída Gui vytváří uživatelské rozhrání aplikace a veškeré komponenty
 *
 * @author Bc. Petr Kokeš
 * @version 2.00
 */
public class Gui {

    private JFrame uvodniOknoFrame;
    private JMenuBar listaMenuBar;
    private JMenu menuMenu;
    private JMenu helpMenu;
    private JMenu serverMenu;
    private JMenuItem startServerMenuItem;
    private JMenuItem stopServerMenuItem;
    private JMenuItem changeServerMenuItem;
    private JMenuItem konecMenuItem;
    private JMenuItem oProgramuMenuItem;
    private JMenuItem napovedaKAplikaciMenuItem;
    private JMenuItem zmenitFtpMenuItem;
    private JMenuItem downloadFtpMenuItem;
    private JMenuItem uploadFtpMenuItem;
    private JTabbedPane zalozkyTabbedPane;
    private JTable odevzdaneUkolyTable;
    private JTable statickeStrankyTable;
    private JTable studentiTable;
    private JTable ukolyTable;
    private JTable publikaceTable;
    private JPanel odevzdaneUkolyPanel;
    private JPanel statickeStrankyPanel;
    private JPanel studentiPanel;
    private JPanel publikacePanel;
    private JPanel ukolyPanel;
    private JButton prohlizejUkolyButton;
    private JButton pridejStrankuButton;
    private JButton editujStatickeStrankyButton;
    private JButton pridejStudentaButton;
    private JButton editujStudenta;
    private JButton editujPublikaceButton;
    private JButton pridejFirmuButton4;
    private JButton editujFirmuButton4;
    private TableModel modelStatickeStranky;
    private TableModel modelStudenti;
    private TableModel modelOdevzdaneUkoly;
    private TableModel modelPublikace;
    private TableModel modelUkoly;
    private OknoZalohujFtp oknoZalohujFtp;
    private OknoZmenitFtpServer oknoZmenitFtpServer;
    private OknoDownloadZFtp oknoDownloadZFtp;
    private Nacitani nacitani;
    private Gui gui;
    public PraceSDaty archiv;
    private Evidence evidence;
    private FtpClient ftpClient;
    private JMenu ftpMenu;
    private TableRowSorter<TableModel> sorterStatic;
    private TableRowSorter<TableModel> sorterPublikace;
    private RowSorter<? extends TableModel> sorterUkoly;
    private TableRowSorter<TableModel> sorterStudenti;
    private TableRowSorter<TableModel> sorterOdevzdaneUkoly;
    private JMenuItem downloadMenuItem;
    private JMenuItem uploadMenuItem;
    private ApiClient apiClient;
    private Server server ;
    private Thread serverThread = new Thread(new Server(), "server-process");;

    /**
     * Vnitřní třída implementující ActionListener sloužící nastartovaní server
     * procesu pro aplikaci pro testování domácích úkolů
     */
    private class StartServer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                serverThread.start();
                JOptionPane.showMessageDialog(null, "Server started");
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící zastavení server
     * procesu pro aplikaci pro testování domácích úkolů
     */
    private class StopServer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                serverThread.stop();
                JOptionPane.showMessageDialog(null, "Server stoped");
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * aktualizaci
     */
    private class UploadAll implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                OknoUpload upload = new OknoUpload(evidence, archiv, ftpClient);
                upload.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * stažení dat
     */
    private class DownloadAll implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                OknoDownload download = new OknoDownload(evidence, archiv, ftpClient);
                download.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna
     * editace publikace
     */
    private class EditujPublikaci implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (publikaceTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "Musíte označit řádek, kterou chcete editovat!!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int row = publikaceTable.getSelectedRow();
                Object id = publikaceTable.getValueAt(publikaceTable.convertRowIndexToView(row), 0);

                OknoHandleData handle = new OknoHandleData(1, 2, evidence.pub, evidence, archiv, id.toString());
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * editaci ukolů
     */
    private class EditujUkoly implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (ukolyTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "Musíte označit řádek, kterou chcete editovat!!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int row = ukolyTable.getSelectedRow();
                Object id = ukolyTable.getValueAt(ukolyTable.convertRowIndexToView(row), 0);

                OknoHandleData handle = new OknoHandleData(2, 2, evidence.pub, evidence, archiv, id.toString());
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * editace studenta
     */
    private class EditujStudenta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (studentiTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "Musíte označit řádek, kterou chcete editovat!!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int row = studentiTable.getSelectedRow();
                Object id = studentiTable.getValueAt(studentiTable.convertRowIndexToView(row), 0);

                OknoHandleData handle = new OknoHandleData(3, 2, evidence.pub, evidence, archiv, id.toString());
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * editaci stránky
     */
    private class EditujStranku implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (statickeStrankyTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "Musíte označit řádek, kterou chcete editovat!!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int row = statickeStrankyTable.getSelectedRow();
                Object id = statickeStrankyTable.getValueAt(statickeStrankyTable.convertRowIndexToView(row), 0);

                OknoHandleData handle = new OknoHandleData(4, 2, evidence.pub, evidence, archiv, id.toString());
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * ukázání odezdaného úkolu
     */
    private class UkazUkol implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (odevzdaneUkolyTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "Musíte označit řádek, kterou chcete editovat!!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int row = odevzdaneUkolyTable.getSelectedRow();
                Object id = odevzdaneUkolyTable.getValueAt(odevzdaneUkolyTable.convertRowIndexToView(row), 0);

                OknoHandleData handle = new OknoHandleData(5, 2, evidence.pub, evidence, archiv, id.toString());
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * přidání publikace
     */
    private class PridejPublikaci implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                OknoHandleData handle = new OknoHandleData(1, 1, evidence.pub, evidence, archiv, "0");
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * přidání ukolu
     */
    private class PridejUkol implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                OknoHandleData handle = new OknoHandleData(2, 1, evidence.pub, evidence, archiv, "0");
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * přidání studenta
     */
    private class PridejStudenta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                OknoHandleData handle = new OknoHandleData(3, 1, evidence.pub, evidence, archiv, "0");
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * přidání stránky
     */
    private class PridejStranku implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                OknoHandleData handle = new OknoHandleData(4, 1, evidence.pub, evidence, archiv, "0");
                handle.setVisible(true);
                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Vnitřní třída implementující ActionListener její metoda actionPerformed
     * je volána z menu pro ukončení aplikace.
     */
    private class KonecAplikace implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Vnitřní třída implementující ActionListener její metoda actionPerformed
     * je volána z menu pro otvoreni okna s informaci o programu
     */
    private class OProgramu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(null,
                    "<html><body><h2>WEB API aplikation</h2>"
                    + "<p><h3>Vývoj: Petr Kokeš<h3/></p>"
                    + "<p>Verze: 004/2014</p>"
                    + "</body></html>");
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * Zálohování nových dat na FTP server
     */
    private class ZalohujFtp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            oknoZalohujFtp = new OknoZalohujFtp(evidence, archiv, ftpClient);
            oknoZalohujFtp.setVisible(true);

            statickeStrankyTable.revalidate();
            studentiTable.revalidate();
            ukolyTable.revalidate();
            publikaceTable.revalidate();
            odevzdaneUkolyTable.revalidate();
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * změnu serveru
     */
    private class ChangeServer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            oknoZmenitFtpServer = new OknoZmenitFtpServer(evidence, archiv, ftpClient, apiClient, evidence.ftpLogin, 1);
            oknoZmenitFtpServer.setVisible(true);

            statickeStrankyTable.revalidate();
            studentiTable.revalidate();
            ukolyTable.revalidate();
            publikaceTable.revalidate();
            odevzdaneUkolyTable.revalidate();

        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * změnu FTP servru
     */
    private class ZměnitFTP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            oknoZmenitFtpServer = new OknoZmenitFtpServer(evidence, archiv, ftpClient, apiClient, evidence.ftpLogin, 0);
            oknoZmenitFtpServer.setVisible(true);
        }
    }

    /**
     * Vnitřní třída implementující ActionListener sloužící otevření okna pro
     * stažení dat z FTP
     */
    private class DownloadFTP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                oknoDownloadZFtp = new OknoDownloadZFtp(evidence, archiv, ftpClient);
                oknoDownloadZFtp.setVisible(true);

                statickeStrankyTable.revalidate();
                studentiTable.revalidate();
                ukolyTable.revalidate();
                publikaceTable.revalidate();
                odevzdaneUkolyTable.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Konstruktor třídy GUI pro vytvoření přiřazení všech potřebných instancí,
     * dat a modelů
     *
     * @param modelStudenti
     * @param modelStatickeStranky
     * @param modelOdevzdaneUkoly
     * @param modelPublikace
     * @param modelUkoly
     * @param archiv
     * @param evidence
     * @param ftpClient
     * @param nacitani
     * @throws Exception
     */
    public Gui(TableModel modelStudenti, TableModel modelStatickeStranky, TableModel modelOdevzdaneUkoly, TableModel modelPublikace, TableModel modelUkoly, PraceSDaty archiv, Evidence evidence, FtpClient ftpClient, Nacitani nacitani) throws Exception {
        this.evidence = evidence;
        this.ftpClient = ftpClient;
        this.modelStatickeStranky = modelStatickeStranky;
        this.modelStudenti = modelStudenti;
        this.modelOdevzdaneUkoly = modelOdevzdaneUkoly;
        this.modelPublikace = modelPublikace;
        this.modelUkoly = modelUkoly;
        this.archiv = archiv;
        this.nacitani = nacitani;
        this.nacitani.NacitaciOkno.setVisible(false);
        this.apiClient = new ApiClient(this.evidence);
        init();
        uvodniOknoFrame.revalidate();
    }

    /*
     * Metoda vytváří hlavní okno a všechny jeho prvky
     */
    private void init() {
        gui = this;
        uvodniOknoFrame = new JFrame();
        uvodniOknoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uvodniOknoFrame.setTitle("Remote administrator");
        uvodniOknoFrame.setSize(1200, 600);
        uvodniOknoFrame.setLocation(150, 200);
        uvodniOknoFrame.setLayout(null);
        uvodniOknoFrame.setVisible(true);
        uvodniOknoFrame.setResizable(false);
        odevzdaneUkolyPanel = new JPanel();
        statickeStrankyPanel = new JPanel();
        studentiPanel = new JPanel();
        ukolyPanel = new JPanel();
        publikacePanel = new JPanel();

        initMenu();
        initTables();
        initButtons();
        initTabs();
    }

    /**
     * Metoda vytváří Menu aplikace
     */
    private void initMenu() {
        listaMenuBar = new JMenuBar();
        menuMenu = new JMenu("Soubor");
        menuMenu.setMnemonic(KeyEvent.VK_S);
        ftpMenu = new JMenu("FTP");
        ftpMenu.setMnemonic(KeyEvent.VK_F);
        helpMenu = new JMenu("Pomoc");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        serverMenu = new JMenu("Server");
        serverMenu.setMnemonic(KeyEvent.VK_S);
        listaMenuBar.add(menuMenu);
        listaMenuBar.add(ftpMenu);
        listaMenuBar.add(serverMenu);
        listaMenuBar.add(helpMenu);

        zmenitFtpMenuItem = new JMenuItem("Změnit FTP server");
        zmenitFtpMenuItem.setVisible(false);
        zmenitFtpMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        zmenitFtpMenuItem.setMnemonic(KeyEvent.VK_F);
        zmenitFtpMenuItem.addActionListener(new ZměnitFTP());
        downloadFtpMenuItem = new JMenuItem("Stažení souborů");
        downloadFtpMenuItem.setVisible(true);
        downloadFtpMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
        downloadFtpMenuItem.setMnemonic(KeyEvent.VK_D);
        downloadFtpMenuItem.addActionListener(new DownloadFTP());

        changeServerMenuItem = new JMenuItem("Změnit server data");
        changeServerMenuItem.setVisible(false);
        changeServerMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        changeServerMenuItem.addActionListener(new ChangeServer());
        changeServerMenuItem.setMnemonic(KeyEvent.VK_I);
        downloadMenuItem = new JMenuItem("Stáhhnout data");
        downloadMenuItem.setVisible(true);
        downloadMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        downloadMenuItem.addActionListener(new DownloadAll());
        downloadMenuItem.setMnemonic(KeyEvent.VK_I);
        uploadMenuItem = new JMenuItem("Nahrát data");
        uploadMenuItem.setVisible(true);
        uploadMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        uploadMenuItem.addActionListener(new UploadAll());
        uploadMenuItem.setMnemonic(KeyEvent.VK_I);
        konecMenuItem = new JMenuItem("Konec");
        konecMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl K"));
        konecMenuItem.setMnemonic(KeyEvent.VK_K);
        konecMenuItem.addActionListener(new KonecAplikace());
        oProgramuMenuItem = new JMenuItem("O Programu");
        oProgramuMenuItem.addActionListener(new OProgramu());
        oProgramuMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        oProgramuMenuItem.setMnemonic(KeyEvent.VK_R);
        startServerMenuItem = new JMenuItem("Start server");
        startServerMenuItem.addActionListener(new StartServer());
        startServerMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        startServerMenuItem.setMnemonic(KeyEvent.VK_S);
        stopServerMenuItem = new JMenuItem("Stop server");
        stopServerMenuItem.addActionListener(new StopServer());
        stopServerMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        stopServerMenuItem.setMnemonic(KeyEvent.VK_T);

        menuMenu.add(downloadMenuItem);
        menuMenu.add(uploadMenuItem);
        menuMenu.addSeparator();
        menuMenu.add(changeServerMenuItem);
        menuMenu.addSeparator();
        menuMenu.add(konecMenuItem);

        ftpMenu.add(zmenitFtpMenuItem);
        ftpMenu.add(downloadFtpMenuItem);

        serverMenu.add(startServerMenuItem);
        serverMenu.add(stopServerMenuItem);

        helpMenu.add(oProgramuMenuItem);
        uvodniOknoFrame.setJMenuBar(listaMenuBar);
    }

    /**
     * Metoda vytváří tlačítka a nastavuje ich rozložení - GridLayout
     */
    private void initButtons() {

        prohlizejUkolyButton = new JButton("Ukaž úkol");
        prohlizejUkolyButton.setVisible(false);
        odevzdaneUkolyPanel.add(prohlizejUkolyButton);
        prohlizejUkolyButton.addActionListener(new UkazUkol());

        pridejStrankuButton = new JButton("Přidat Stránku");
        pridejStrankuButton.setVisible(false);
        statickeStrankyPanel.add(pridejStrankuButton);
        pridejStrankuButton.addActionListener(new PridejStranku());

        editujStatickeStrankyButton = new JButton("Edituj stránku");
        editujStatickeStrankyButton.setVisible(false);
        statickeStrankyPanel.add(editujStatickeStrankyButton);
        editujStatickeStrankyButton.addActionListener(new EditujStranku());

        pridejStudentaButton = new JButton("Přidej studenta");
        pridejStudentaButton.setVisible(false);
        studentiPanel.add(pridejStudentaButton);
        pridejStudentaButton.addActionListener(new PridejStudenta());

        editujStudenta = new JButton("Edituj studenta");
        editujStudenta.setVisible(false);
        studentiPanel.add(editujStudenta);
        editujStudenta.addActionListener(new EditujStudenta());

        pridejFirmuButton4 = new JButton("Přidej úkol");
        pridejFirmuButton4.setVisible(false);
        ukolyPanel.add(pridejFirmuButton4);
        pridejFirmuButton4.addActionListener(new PridejUkol());

        editujFirmuButton4 = new JButton("Edituj úkol");
        editujFirmuButton4.setVisible(false);
        ukolyPanel.add(editujFirmuButton4);
        editujFirmuButton4.addActionListener(new EditujUkoly());

        editujPublikaceButton = new JButton("Edituj publikaci");
        editujPublikaceButton.setVisible(false);
        publikacePanel.add(editujPublikaceButton);
        editujPublikaceButton.addActionListener(new EditujPublikaci());

        odevzdaneUkolyPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        if (shouldFill) {
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 1;
        odevzdaneUkolyPanel.add(prohlizejUkolyButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.ipady = 190;
        c.gridwidth = 6;
        c.gridx = 0;
        c.gridy = 3;
        odevzdaneUkolyPanel.add(new JScrollPane(odevzdaneUkolyTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);

        statickeStrankyPanel.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        if (shouldFill) {
            d.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) {
            d.weightx = 1;
        }

        d.fill = GridBagConstraints.HORIZONTAL;
        d.weightx = 1;
        d.weighty = 1;
        d.gridx = 1;
        d.gridy = 1;
        statickeStrankyPanel.add(pridejStrankuButton, d);

        d.fill = GridBagConstraints.HORIZONTAL;
        d.weightx = 1;
        d.weighty = 1;
        d.gridx = 2;
        d.gridy = 1;
        statickeStrankyPanel.add(editujStatickeStrankyButton, d);

        d.fill = GridBagConstraints.HORIZONTAL;
        d.weightx = 1;
        d.weighty = 1;
        d.ipady = 190;
        d.gridwidth = 6;
        d.gridx = 0;
        d.gridy = 3;
        statickeStrankyPanel.add(new JScrollPane(statickeStrankyTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), d);

        studentiPanel.setLayout(new GridBagLayout());
        GridBagConstraints f = new GridBagConstraints();

        if (shouldFill) {
            f.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) {
            f.weightx = 0.5;
        }

        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1;
        f.weighty = 1;
        f.gridx = 1;
        f.gridy = 1;
        studentiPanel.add(pridejStudentaButton, f);

        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1;
        f.weighty = 1;
        f.gridx = 2;
        f.gridy = 1;
        studentiPanel.add(editujStudenta, f);

        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1;
        f.weighty = 1;
        f.ipady = 190;
        f.gridwidth = 6;
        f.gridx = 0;
        f.gridy = 3;
        studentiPanel.add(new JScrollPane(studentiTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), f);

        publikacePanel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        if (shouldFill) {
            f.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) {
            f.weightx = 0.5;
        }

        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;
        g.weighty = 1;
        g.gridx = 2;
        g.gridy = 1;
        publikacePanel.add(editujPublikaceButton, g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;
        g.weighty = 1;
        g.ipady = 190;
        g.gridwidth = 6;
        g.gridx = 0;
        g.gridy = 3;
        publikacePanel.add(new JScrollPane(publikaceTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), g);

        ukolyPanel.setLayout(new GridBagLayout());
        GridBagConstraints e = new GridBagConstraints();

        if (shouldFill) {
            e.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) {
            e.weightx = 0.5;
        }

        e.fill = GridBagConstraints.HORIZONTAL;
        e.weightx = 1;
        e.weighty = 1;
        e.gridx = 1;
        e.gridy = 1;
        ukolyPanel.add(pridejFirmuButton4, e);

        e.fill = GridBagConstraints.HORIZONTAL;
        e.weightx = 1;
        e.weighty = 1;
        e.gridx = 2;
        e.gridy = 1;
        ukolyPanel.add(editujFirmuButton4, e);

        e.fill = GridBagConstraints.HORIZONTAL;
        e.weightx = 1;
        e.weighty = 1;
        e.ipady = 190;
        e.gridwidth = 6;
        e.gridx = 0;
        e.gridy = 3;
        ukolyPanel.add(new JScrollPane(ukolyTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), e);

    }

    /**
     * Metoda vytváří tabulky v jednotlyvých záložkách
     */
    private void initTables() {
        odevzdaneUkolyTable = new JTable(modelOdevzdaneUkoly);
        odevzdaneUkolyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        odevzdaneUkolyTable.setPreferredScrollableViewportSize(new Dimension(500, 230));
        odevzdaneUkolyTable.setAutoCreateRowSorter(true);
        sorterOdevzdaneUkoly = new TableRowSorter<TableModel>(odevzdaneUkolyTable.getModel());
        odevzdaneUkolyTable.setRowSorter(sorterOdevzdaneUkoly);

        statickeStrankyTable = new JTable(modelStatickeStranky);
        statickeStrankyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        statickeStrankyTable.setPreferredScrollableViewportSize(new Dimension(500, 230));
        statickeStrankyTable.setAutoCreateRowSorter(true);
        sorterStatic = new TableRowSorter<TableModel>(statickeStrankyTable.getModel());
        statickeStrankyTable.setRowSorter(sorterStatic);

        studentiTable = new JTable(modelStudenti);
        studentiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentiTable.setPreferredScrollableViewportSize(new Dimension(500, 230));
        studentiTable.setAutoCreateRowSorter(true);
        sorterStudenti = new TableRowSorter<TableModel>(studentiTable.getModel());
        studentiTable.setRowSorter(sorterStudenti);

        ukolyTable = new JTable(modelUkoly);
        ukolyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ukolyTable.setPreferredScrollableViewportSize(new Dimension(500, 230));
        ukolyTable.setAutoCreateRowSorter(true);
        sorterUkoly = new TableRowSorter<TableModel>(ukolyTable.getModel());
        ukolyTable.setRowSorter(sorterUkoly);

        publikaceTable = new JTable(modelPublikace);
        publikaceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        publikaceTable.setPreferredScrollableViewportSize(new Dimension(500, 230));
        publikaceTable.setAutoCreateRowSorter(true);
        sorterPublikace = new TableRowSorter<TableModel>(publikaceTable.getModel());
        publikaceTable.setRowSorter(sorterPublikace);

    }

    /**
     * Metoda vytváří rozšírené možnosti po přihlášení pracovníka firmy. Vytvára
     * záložky neplaticích vip a platicích s rozšířenou funkcionalitou
     */
    public void initTabs() {
        zalozkyTabbedPane = new JTabbedPane();
        uvodniOknoFrame.add(zalozkyTabbedPane);
        zalozkyTabbedPane.setBounds(4, 5, 1190, 550);
        zalozkyTabbedPane.addTab("Publikace", publikacePanel);
        zalozkyTabbedPane.addTab("Studenti", studentiPanel);
        zalozkyTabbedPane.addTab("Úkoly", ukolyPanel);
        zalozkyTabbedPane.addTab("Odevzdané testy", odevzdaneUkolyPanel);
        prohlizejUkolyButton.setVisible(true);
        pridejStrankuButton.setVisible(true);
        editujStatickeStrankyButton.setVisible(true);
        pridejStudentaButton.setVisible(true);
        editujStudenta.setVisible(true);
        editujPublikaceButton.setVisible(true);
        pridejFirmuButton4.setVisible(true);
        editujFirmuButton4.setVisible(true);
        changeServerMenuItem.setVisible(true);
        downloadFtpMenuItem.setVisible(true);
        zmenitFtpMenuItem.setVisible(true);
    }

    /**
     * Vnitřní třída spuštěná v samostatném treatu pro kontrolu vstupní
     * komunikace
     */
    public class Server implements Runnable {

        private static final int PORT = 6666;
        private final Logger LOG = Logger.getLogger("");
        private ServerSocket serverSocket;
        private GameManager roomManager;

        public void run() {
            try {
                serverSocket = new ServerSocket(PORT);

                roomManager = new GameManager(evidence);
                System.out.println("Server started");
                while (true) {
                    new ServerThread(serverSocket.accept(), roomManager).start();
                }

            } catch (BindException b) {
//                LOG.error("Server is already running!", b);
            } catch (IOException e) {
//                LOG.error("Error creating server!", e);
//                System.exit(-1);
            }
        }

    }

}
