package model;

import javax.swing.table.AbstractTableModel;
import logika.Evidence;
import logika.Student;

/**
 * Třída ModelStudenti vytváří model tabulky s položkami studentů
 *
 * @author Petr Kokeš
 * @version 1.00
 */
public class ModelStudenti extends AbstractTableModel {

    private Evidence evidence;
    String[] zahlaviSloupcu = {"ID","Uživatelské jméno", "Jméno", "Příjmení", "Společnost", "Ulice", "Město","Kraj","Stát","E-mail","ID školy","ID fakulty"};

    /**
     * Konstruktor vytvárejíci model tabulky studentů
     *     
* @param evidence
     */
    public ModelStudenti(Evidence evidence) {
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
        return evidence.student.size();
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

        Student item = evidence.student.get(row);
        switch (col) {
            case 0:
                return (item.getId());
            case 1:
                return (item.getUsername());
            case 2:
                return (item.getName());
            case 3:
                return (item.getSurname());
            case 4:
                return (item.getCompany());
            case 5:
                return (item.getStreet()) + " " + (item.getStreetNumber());
            case 6:
                return (item.getCity());
            case 7:
                return (item.getDiscrict());
            case 8:
                return (item.getState());
            case 9:
                return (item.getEmail()); 
            case 10:
                return (item.getIdSkoly());
            case 11:
                return (item.getIdFakulty());
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
