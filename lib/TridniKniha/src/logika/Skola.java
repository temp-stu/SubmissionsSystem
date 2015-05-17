package logika;

/**
 *Třída datového typu škola
 * @author Petr Kokeš
 */
public class Skola {

    private String id;
    private String nazev;
    private String poradi;
    private String viditelnost;

    public Skola(String id, String nazev, String poradi, String viditelnost) {

        this.id = id;
        this.nazev = nazev;
        this.poradi = poradi;
        this.viditelnost = viditelnost;

    }

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
