
package logika;

/**
 *Třída datového typu předmět
 * @author Petr Kokeš
 */
public class Predmet {

    private String id;
    private String nazev;
    private String poradi;
    private String viditelnost;
    private String idSkoly;
    private String idFakulty;
    private String idStatic;

    public Predmet(String id, String nazev, String poradi, String viditelnost, String idSkoly,String idFakulty, String idStatic) {

        this.id = id;
        this.nazev = nazev;
        this.poradi = poradi;
        this.viditelnost = viditelnost;
        this.idSkoly = idSkoly;
        this.idFakulty = idFakulty;
        this.idStatic = idStatic;
    }
     public String getIdStatic() {
        return idStatic;
    }
     
      public String getIdFakulty() {
        return idFakulty;
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
