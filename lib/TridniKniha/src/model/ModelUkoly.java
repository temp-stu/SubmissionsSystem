package model;

import javax.swing.table.AbstractTableModel;
import logika.Evidence;
import logika.Ukol;

/**
 * Třída ModelUkoly vytváří model tabulky s položkami ukolů
 *
 * @author Petr Kokeš
 * @version 1.00
 */
public class ModelUkoly  extends AbstractTableModel  {
 private Evidence evidence;
    String[] zahlaviSloupcu = {"ID","Název","Typ","Max bodů","Odevzdání od","Odevzdání do", "Pořadi", "Viditelnost", "ID školy", "ID fakulty", "ID Statické stránky"};

    /**
     * Konstruktor vytvárejíci model tabulky ukolů
     *     
* @param evidence
     */
    public ModelUkoly(Evidence evidence) {
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
        return evidence.ukol.size();
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
        Ukol item = evidence.ukol.get(row);
        switch (col) {
            case 0:
                return (item.getId());
            case 1:
                return (item.getNazev());
            case 2:
                return (item.getTyp());
            case 3:
                return (item.getMaxBody());
            case 4:
                return (item.getDatumOd());
            case 5:
                return (item.getDatumDo());
            case 6:
                return (item.getPoradi());
            case 7:
                if(item.getViditelnost().equals("1")){
                   return "ANO"; 
                }else{
                   return "NE"; 
                }
            case 8:
                return (item.getIdSkoly());
            case 9:
                return (item.getIdFakulty());
            case 10:
                return (item.getIdStatic());                                                                                                                
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
