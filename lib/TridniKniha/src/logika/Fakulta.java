
package logika;

/**
 *Tato třída symbolizuje fakultu
 * @author Petr Kokeš
 */
public class Fakulta {

    private String id;
    private String nazev;
    private String poradi;
    private String viditelnost;
    private String idSkoly;
    private String idStatic;

    public Fakulta(String id, String nazev, String poradi, String viditelnost, String idSkoly, String idStatic) {

        this.id = id;
        this.nazev = nazev;
        this.poradi = poradi;
        this.viditelnost = viditelnost;
        this.idSkoly = idSkoly;
        this.idStatic = idStatic;
    }
     public String getIdStatic() {
        return idStatic;
    }
    
     public String getIdSkoly() {
return idSkoly;    }

    public String getViditelnost() {
        return viditelnost;
    }

    public String getPoradi() {
        return poradi;
    }

    public String getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

}
