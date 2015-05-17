
package model;

import javax.swing.table.AbstractTableModel;
import logika.Evidence;
import logika.*;

/**
 * Třída ModelStatickeStránky vytváří model tabulku s odevzadné publikacemi
 * 
* @author Petr Kokeš
 * @version 1.00
 */
public class ModelPublikace extends AbstractTableModel {

    private Evidence evidence;
    String[] zahlaviSloupcu = {"ID","Název", "Soubor", "URL", "Subdoména", "Viditelnost", "Poradi","ID kategorie","MIME typ","Přípona","Velikost","Vloženo"};

    /**
     * Konstruktor vytvárejíci model tabulky publikacemi
     *     
* @param evidence
     */
    public ModelPublikace(Evidence evidence) {
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
        return evidence.publikace.size();
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
        return getValueAt(0, c).getClass();
    }

    /**
     * Metoda vyplní sloupce tabulky hodnotami z evidence
     */
    @Override
    public Object getValueAt(int row, int col) {
        // naplneni seznamu

        Publikace item = evidence.publikace.get(row);
        
        switch (col) {
            case 0:
                return (item.getId());
            case 1:
                return (item.getNazev());
            case 2:
                return (item.getSoubor());
            case 3:
                return (item.getUrl());
            case 4:
                return (item.getSubdomain());
            case 5:
                if(item.getViditelnost().equals("1")){
                   return "ANO"; 
                }else{
                   return "NE"; 
                }
            case 6:
                return (item.getPoradi());
            case 7:
                return (item.getIdKategorie());
            case 8:
                return (item.getMime());
            case 9:
                return (item.getFileExt());
            case 10:
                return (item.getSize());
            case 11:
                return (item.getCreated());                                                                                                                           
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
