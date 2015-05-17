package model;

import javax.swing.table.AbstractTableModel;
import logika.*;

/**
 * Třída ModelStatickeStranky vytváří tabulku s položkami statických stránek
 student
 *
 * @author Petr Kokeš
 * @version 1.00
 */
public class ModelStatickeStranky extends AbstractTableModel {

    private Evidence evidence;
    String[] zahlaviSloupcu = {"ID","Název", "Title", "Klíčová slova", "Pořadí", "Viditelnost", "URL","Alternativní URL","Subdoména","ID kategorie"};

    /**
     * Konstruktor vytvárejíci model tabulky statických stránek
     *     
* @param evidence
     */
    public ModelStatickeStranky(Evidence evidence) {
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
        return evidence.staticke.size();
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

        StatickeStranky item = evidence.staticke.get(row);
        switch (col) {
            case 0:
                return (item.getId());
            case 1:
                return (item.getNazev());
            case 2:
                return (item.getTitle());
            case 3:
                return (item.getKeywords());
            case 4:
                return (item.getPoradi());
            case 5:
                if(item.getViditelnost().equals("1")){
                   return "ANO"; 
                }else{
                   return "NE"; 
                }
            case 6:
                return (item.getUrl());
            case 7:
                return (item.getUrla());
            case 8:
                return (item.getSubdomain());
            case 9:
                return (item.getIdKategorie());                                                                                                                            
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
