package ui;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import logika.*;

/**
 * Univerzální třída pro vytvoření editačního či přdávacího okna
* @author Petr Kokeš
 * @version 1.00
 */
public class OknoHandleData {

    public JDialog oknoAkceDialog;
    private JLabel[] labelArray;
    private JTextField[] textFieldArray;
    private JComboBox[] comboArray;
    private JButton okButton;
    public Evidence evidence;
    public PraceSDaty praceSDaty;
    private Ukol ukol;
    private int mode;
    private int readmode;
    private File file;
    private PraceSDaty archiv;
    private String id;
    private int currentYPosition;
    private String[][] data;
    private int yPosSec;
    private JButton download;
    private JButton upload;
    private File uploadFile;
    private int toDelete;

    /**
     * Konstruktor vytvářející okno s formulářem
     */
    public OknoHandleData(int mode, int readmode, File file, Evidence evidence, PraceSDaty archiv, String id) throws IOException {
        this.evidence = evidence;
        this.mode = mode;
        this.readmode = readmode;
        this.file = file;
        this.archiv = archiv;
        this.id = id;
        init();
    }
    
    /**
     * Vnitřní třída implementující ActionListener slouží k otevření složky se souborem
     * databáze
     */
    private class OpenFolder implements ActionListener {
        private String cesta;
        private OpenFolder(String cesta){
            this.cesta = cesta;
        }

        @Override
        public void actionPerformed(ActionEvent udalost) {
            String uprava = cesta.replaceAll("/data/web/virtuals/[0-9]+/virtual/www/", "");
            uprava = uprava.replaceAll("/var/www/edu-pgm.org/htdocs/", "");
            String localPath = evidence.localPath+"\\web\\data\\FTP\\download\\"+uprava;
//            //System.out.print(uprava);
            System.out.print(localPath);
            File myfile = new File(localPath);
                String path = myfile.getAbsolutePath();  
                File dir = new File(path.substring(0, path.lastIndexOf(File.separator)));
                if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(dir);
                } catch (IOException ex) {
                    Logger.getLogger(OknoHandleData.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        }
    }
    
     /**
     * Vnitřní třída implementující ActionListener slouží k uložení souboru
     */
    private class SaveFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent udalost) {
              final JFileChooser fc = new JFileChooser();
            Component eComponent = null;
int returnVal = fc.showOpenDialog(eComponent);
if (returnVal == JFileChooser.APPROVE_OPTION) {
    FileInputStream inStream = null;
                  try {
                      File file = fc.getSelectedFile();
                      uploadFile = file;
                      File afile = uploadFile;
                      File bfile =new File(evidence.localPath+"\\web\\data\\FTP\\upload\\publikace\\"+uploadFile.getName());
                      inStream = new FileInputStream(afile);
                      FileOutputStream outStream = new FileOutputStream(bfile);
                      byte[] buffer = new byte[1024];
                      int length;
                      while ((length = inStream.read(buffer)) > 0){
                          
                          outStream.write(buffer, 0, length);
                          
                      }         inStream.close();
            outStream.close();
                  } catch (FileNotFoundException ex) {
                      Logger.getLogger(OknoHandleData.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IOException ex) {
                      Logger.getLogger(OknoHandleData.class.getName()).log(Level.SEVERE, null, ex);
                  } finally {
                      try {
                          inStream.close();
                      } catch (IOException ex) {
                          Logger.getLogger(OknoHandleData.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }

            } else {
                JOptionPane.showMessageDialog(null,
                        "Přerušeno uživatelem!!",
                        "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    /**
     * Vnitřní třída implementující ActionListener slouží k pridávací či editační akce
     * databáze
     */
    private class PridaniAkce implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent udalost) {
            int counter = 0;
            String csvString = "";
      for (String[] field : data) {
           String text = "";
           try{
               if (field[2].equals("1")) {
                text = textFieldArray[counter].getText();
            }
            if (field[2].equals("2")) {
                if(readmode == 1){
                text = "./www/files/publikace/"+uploadFile.getName();
                }else{
            text = field[1];   }
            }
            if (field[2].equals("3")) {
                text = evidence.skola.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("4")) {
                text = evidence.fakulta.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("5")) {
                text = evidence.predmety.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("6")) {
                text = evidence.skupina.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("7")) {
                text = evidence.staticke.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("8")) {
                text = evidence.student.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("9")) {
                text = Integer.toString(comboArray[counter].getSelectedIndex());
            }
            if (field[2].equals("10")) {
                text = evidence.publikacekat.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("11")) {
                text = evidence.statickat.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("12")) {
                text = evidence.ukol.get(comboArray[counter].getSelectedIndex()).getId();
            }
            if (field[2].equals("13")) {
                text = evidence.skola.get(comboArray[counter].getSelectedIndex()).getId();
            }            
               } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
           if((data.length-1) == counter){
               csvString = csvString+text;
           }else{
               csvString = csvString+text+";";
           }
           counter++;
        }

      
            try {
                archiv.aktualizujMainData(mode,readmode,csvString,toDelete);
            } catch (IOException ex) {
                Logger.getLogger(OknoHandleData.class.getName()).log(Level.SEVERE, null, ex);
            }
            oknoAkceDialog.dispose();
        }
    }

    /**
     * Metoda vytváří okno s jednotlivými komponentami
     */
    private void init() throws IOException {
        if (mode == 1) {
            data = evidence.publikace.get(0).getArrayInfo();
            for (int i = 0; i < evidence.publikace.size(); i++) {
                if (evidence.publikace.get(i).getId().equals(id)) {
                    data = evidence.publikace.get(i).getArrayInfo();
                    toDelete = i;
                }
            }
            comboArray = new JComboBox[data.length];
            labelArray = new JLabel[data.length];
            textFieldArray = new JTextField[data.length];
        }
        
        if (mode == 2) {
            data = evidence.ukol.get(0).getArrayInfo();
            for (int i = 0; i < evidence.ukol.size(); i++) {
                if (evidence.ukol.get(i).getId().equals(id)) {
                    data = evidence.ukol.get(i).getArrayInfo();
                    toDelete = i;
                }
            }
            comboArray = new JComboBox[data.length];
            labelArray = new JLabel[data.length];
            textFieldArray = new JTextField[data.length];
        }
        
        if (mode == 3) {
            data = evidence.student.get(0).getArrayInfo();
            for (int i = 0; i < evidence.student.size(); i++) {
                if (evidence.student.get(i).getId().equals(id)) {
                    data = evidence.student.get(i).getArrayInfo();
                    toDelete = i;
                }
            }
            comboArray = new JComboBox[data.length];
            labelArray = new JLabel[data.length];
            textFieldArray = new JTextField[data.length];
        }
        
        if (mode == 4) {
            data = evidence.staticke.get(0).getArrayInfo();
            for (int i = 0; i < evidence.staticke.size(); i++) {
                if (evidence.staticke.get(i).getId().equals(id)) {
                    data = evidence.staticke.get(i).getArrayInfo();
                    toDelete = i;
                }
            }
            comboArray = new JComboBox[data.length];
            labelArray = new JLabel[data.length];
            textFieldArray = new JTextField[data.length];
        }
        
        if (mode == 5) {
            data = evidence.userukol.get(0).getArrayInfo();
            for (int i = 0; i < evidence.userukol.size(); i++) {
                if (evidence.userukol.get(i).getId().equals(id)) {
                    data = evidence.userukol.get(i).getArrayInfo();
                    toDelete = i;
                }
            }
            comboArray = new JComboBox[data.length];
            labelArray = new JLabel[data.length];
            textFieldArray = new JTextField[data.length];
        }

        oknoAkceDialog = new JDialog();
        oknoAkceDialog.setVisible(true);
        oknoAkceDialog.setTitle("Práce se záznamy");
        oknoAkceDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        generate();
        oknoAkceDialog.setSize(700, yPosSec);
        oknoAkceDialog.setBounds(200, 200, 700, yPosSec);
        oknoAkceDialog.setLayout(null);
        oknoAkceDialog.setVisible(true);
        oknoAkceDialog.setResizable(false);

        okButton = new JButton("OK");
        oknoAkceDialog.add(okButton);
        okButton.setBounds(455, yPosSec - 70, 100, 30);
        okButton.addActionListener(new PridaniAkce());
    }

    private void generate() throws IOException {
        boolean sudy = false;
        int xPos = 25;
        int yPos = 0;
        int xPosSec = 50;
        yPosSec = 0;
        int counter = 0;
        for (String[] field : data) {
            int docasneX = 25;
            int docasneXSec = 150;
            if (sudy == true) {
                docasneX = 355;
                docasneXSec = 470;
            } else {
                docasneX = 25;
                docasneXSec = 150;
                yPos = yPos + 25;
                yPosSec = yPosSec + 25;
            }
//                //System.out.println(yPos+" - "+docasneX);
//                //System.out.println(yPosSec+" - "+docasneXSec);
//                //System.out.println(" ");
            labelArray[counter] = new JLabel(field[0]);
            oknoAkceDialog.add(labelArray[counter]);
            labelArray[counter].setBounds(docasneX, yPos, 150, 15);
            if (field[2].equals("1")) {
                textFieldArray[counter] = new JTextField();
                if(readmode == 1){
                textFieldArray[counter].setText("");
                }else{
                textFieldArray[counter].setText(field[1]);
                //System.out.println(field[1]);
                }
                oknoAkceDialog.add(textFieldArray[counter]);
                textFieldArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
            }
            if (field[2].equals("2")) {
                if(readmode == 1){
                upload = new JButton("Set file");
                oknoAkceDialog.add(upload);
            upload.setBounds(docasneXSec, yPosSec, 180, 20);
            upload.addActionListener(new SaveFile());
                }else{
            download = new JButton("Open in Folder");
            oknoAkceDialog.add(download);
            download.setBounds(docasneXSec, yPosSec, 180, 20);
            download.addActionListener(new OpenFolder(field[1]));   }
            }
            if (field[2].equals("3")) {
            String[] kategorie = new String[evidence.skola.size()];
                int select = 0;
                for (int j = 0; j < evidence.skola.size(); j++) {
                    //System.out.println(evidence.skola.get(j).getNazev());
                    kategorie[j] = evidence.skola.get(j).getNazev();
                    if (evidence.skola.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("4")) {
            String[] kategorie = new String[evidence.fakulta.size()];
                int select = 0;
                for (int j = 0; j < evidence.fakulta.size(); j++) {
                    //System.out.println(evidence.fakulta.get(j).getNazev());
                    kategorie[j] = evidence.fakulta.get(j).getNazev();
                    if (evidence.fakulta.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("5")) {
            String[] kategorie = new String[evidence.predmety.size()];
                int select = 0;
                for (int j = 0; j < evidence.predmety.size(); j++) {
                    //System.out.println(evidence.predmety.get(j).getNazev());
                    kategorie[j] = evidence.predmety.get(j).getNazev();
                    if (evidence.predmety.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("6")) {
            String[] kategorie = new String[evidence.skupina.size()];
                int select = 0;
                for (int j = 0; j < evidence.skupina.size(); j++) {
                    for (int k = 0; k < evidence.predmety.size(); k++) {
                        if(evidence.predmety.get(k).getId().equals(evidence.skupina.get(j).getIdPredmetu())){
                    //System.out.println(evidence.predmety.get(k).getNazev());
                    kategorie[j] = evidence.predmety.get(k).getNazev();                    
                        }
                        if (evidence.skupina.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("7")) {
            String[] kategorie = new String[evidence.staticke.size()];
                int select = 0;
                for (int j = 0; j < evidence.staticke.size(); j++) {
                    //System.out.println(evidence.staticke.get(j).getNazev());
                    kategorie[j] = evidence.staticke.get(j).getNazev();
                    if (evidence.staticke.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("8")) {
            String[] kategorie = new String[evidence.student.size()];
                int select = 0;
                for (int j = 0; j < evidence.student.size(); j++) {
                    //System.out.println(evidence.student.get(j).getUsername());
                    kategorie[j] = evidence.student.get(j).getName() + " " + evidence.student.get(j).getSurname();
                    if (evidence.student.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("9")) {
                String[] visible = {"NE", "Ano"};
                comboArray[counter] = new JComboBox(visible);
                comboArray[counter].setSelectedIndex(Integer.parseInt(field[1]));
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);
            }
            if (field[2].equals("10")) {
                String[] kategorie = new String[evidence.publikacekat.size()];
                int select = 0;
                for (int j = 0; j < evidence.publikacekat.size(); j++) {
                    //System.out.println(evidence.publikacekat.get(j).getNazev());
                    kategorie[j] = evidence.publikacekat.get(j).getNazev();
                    if (evidence.publikacekat.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);
            }
            if (field[2].equals("11")) {
            String[] kategorie = new String[evidence.statickat.size()];
                int select = 0;
                for (int j = 0; j < evidence.statickat.size(); j++) {
                    //System.out.println(evidence.statickat.get(j).getNazev());
                    kategorie[j] = evidence.statickat.get(j).getNazev();
                    if (evidence.statickat.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("12")) {
            String[] kategorie = new String[evidence.ukol.size()];
                int select = 0;
                for (int j = 0; j < evidence.ukol.size(); j++) {
                    //System.out.println(evidence.ukol.get(j).getNazev());
                    kategorie[j] = evidence.ukol.get(j).getNazev();
                    if (evidence.ukol.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (field[2].equals("13")) {
            String[] kategorie = new String[evidence.skola.size()];
            kategorie[0]= "Statické stránky";
                int select = 0;
                for (int j = 1; j < evidence.skola.size()+1; j++) {
                    //System.out.println(evidence.skola.get(j).getNazev());
                    kategorie[j] = evidence.skola.get(j).getNazev();
                    if (evidence.skola.get(j).getId().equals(field[1])) {
                        select = j;
                    }
                }
                //System.out.println(kategorie[select]);
                comboArray[counter] = new JComboBox(kategorie);
                comboArray[counter].setSelectedIndex(select);
                comboArray[counter].setBounds(docasneXSec, yPosSec, 200, 20);
                oknoAkceDialog.add(comboArray[counter]);  
            }
            if (sudy == true) {
                sudy = false;
            } else {
                sudy = true;
            }
            counter++;
        }
        yPosSec = yPosSec + 100;
    }


    /**
     * Metoda na zviditelnení okna
     *     
* @param viditelnost
     */
    public void setVisible(boolean viditelnost) {
        if(viditelnost == false){
        oknoAkceDialog.dispose();}
    }
}
