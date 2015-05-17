package model;

import javax.swing.table.AbstractTableModel;
import logika.Evidence;
import logika.UserUkol;

/**
 * Třída ModelOdevzdaneUkoly vytváří model tabulku s odevzadné úkoly
 * 
* @author Petr Kokeš
 * @version 1.00
 */
public class ModelOdevzdaneUkoly extends AbstractTableModel {

    private Evidence evidence;
    String[] zahlaviSloupcu = {"ID","Název","Soubor","body","Odevzdáno","Datum uložení","ID úkolu","Přípona","Velikost","MIME typ"};

    /**
     * Konstruktor vytvárejíci model tabulky odevzadné úkoly
     *     
* @param evidence
     */
    public ModelOdevzdaneUkoly(Evidence evidence) {
        this.evidence = evidence;
    }

    /**
     * Metoda vrací název sloupců tabulky
     *     
* @param col
     */
    @Override
    public String getColumnName(int col) {
        return zahlaviSloupcu[col];
    }

    /**
     * Metoda vrací počet řádků tabulky
     */
    @Override
    public int getRowCount() {
        return evidence.userukol.size();
    }

    /**
     * Metoda vrací počet sloupců tabulky
     */
    @Override
    public int getColumnCount() {
        return zahlaviSloupcu.length;
    }

    /**
     * Metoda vrací hodnotu sloupce tabulky
     */
    @Override
    public Class getColumnClass(int c) {
        int d = 0;
        if(c<20){        
        d = c;
        //System.out.println(d);
        }

        return getValueAt(0, d).getClass();
    }

    /**
     * Metoda vyplní sloupce tabulky hodnotami z evidence
     */
    @Override
    public Object getValueAt(int row, int col) {
        // naplneni seznamu
        UserUkol item = evidence.userukol.get(row);
        switch (col) {
            case 0:
                return (item.getId());
            case 1:
                return (item.getNazev());
            case 2:
                return (item.getSoubor());
            case 3:
                return (item.getBody());
            case 4:
                if(item.getOdevzdano().equals("1")){
                   return "ANO"; 
                }else{
                   return "NE"; 
                }
            case 5:
                return (item.getDatum());
            case 6:
                return (item.getUkolId());
            case 7:
                return (item.getFileExt());
            case 8:
                return (item.getSize()) + " KB";
            case 9:
                return (item.getMime());                                                                                                                              
            default:
                return null;
        }

    }

    /**
     * Metoda na určení editovatelnosti tabulky
     *     
* @param radek
     * @param sloupec
     */
    @Override
    public boolean isCellEditable(int radek, int sloupec) {
        switch (sloupec) {
            default:
                return false;
        }
    }
}
