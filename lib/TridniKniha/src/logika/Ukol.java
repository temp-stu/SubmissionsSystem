package logika;

/**
 *Třída datového typu ukol
 * @author Petr Kokeš
 */
public class Ukol {

    private String id;
    private String nazev;
    private String poradi;
    private String viditelnost;
    private String idSkoly;
    private String idFakulty;
    private String idSkupiny;
    private String idStatic;
    private String typ;
    private String maxbody;
    private String od;
    private String datumdo;

    public Ukol(String id, String nazev, String viditelnost, String poradi, String idSkoly,
            String idFakulty,String idSkupiny, String idStatic, String typ,String maxbody,String od, String datumdo) {

        this.id = id;
        this.nazev = nazev;
        this.poradi = poradi;
        this.viditelnost = viditelnost;
        this.idSkoly = idSkoly;
        this.idFakulty = idFakulty;
        this.idSkupiny = idSkupiny;
        this.idStatic = idStatic;
        this.typ = typ;
        this.maxbody = maxbody;
        this.od = od;
        this.datumdo = datumdo;
    }
    
    public String getDatumOd() {
        return od;
    }
    
    public String getDatumDo() {
        return datumdo;
    }
    
    public String getMaxBody() {
        return maxbody;
    }
    
    public String getTyp() {
        return typ;
    }
    
    public String getIdSkupiny() {
        return idSkupiny;
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
    
        public String[][] getArrayInfo(){
    String[][] info = new String[12][3];
    info[0][0] = "ID";
    info[0][1] = id;
    info[0][2] = "1";
    info[1][0] = "Název";
    info[1][1] = nazev;
    info[1][2] = "1";
    info[2][0] = "Viditelnost";
    info[2][1] = viditelnost;
    info[2][2] = "9";
    info[3][0] = "Poradi";
    info[3][1] = poradi;
    info[3][2] = "1";
    info[4][0] = "Škola";
    info[4][1] = idSkoly;
    info[4][2] = "3";
    info[5][0] = "Fakulta";
    info[5][1] = idFakulty;
    info[5][2] = "4";
    info[6][0] = "Semestr";
    info[6][1] = idSkupiny;
    info[6][2] = "6";
    info[7][0] = "Statická stránka";
    info[7][1] = idStatic;
    info[7][2] = "7";
    info[8][0] = "Typ";
    info[8][1] = typ;
    info[8][2] = "1";
    info[9][0] = "Max bodů";
    info[9][1] = maxbody;
    info[9][2] = "1";
    info[10][0] = "Od";
    info[10][1] = od;
    info[10][2] = "1";
    info[11][0] = "Do";
    info[11][1] = datumdo;
    info[11][2] = "3";
    
    return info;
}

}
