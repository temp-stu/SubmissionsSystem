package logika;

/**
 *Třída datového typu odevzdaný ukol
 * @author Petr Kokeš
 */
public class UserUkol {

    private String id;
    private String nazev;
    private String soubor;
    private String cesta;
    private String popis;
    private String body;
    private String odevzdano;
    private String datum;
    private String userId;
    private String ukolId;
    private String fileExt;
    private String size;
    private String mime;
    private String vysvetleni;

    public UserUkol(String id, String nazev, String soubor, String cesta, String popis, String body,
            String odevzdano, String datum, String userId, String ukolId, String fileExt, String size, String mime) {

        this.id = id;
        this.nazev = nazev;
        this.soubor = soubor;
        this.cesta = cesta;
        this.popis = popis;
        this.body = body;
        this.odevzdano = odevzdano;
        this.datum = datum;
        this.userId = userId;
        this.ukolId = ukolId;
        this.fileExt = fileExt;
        this.size = size;
        this.mime = mime;        
    }

    public String getBody() {
        return body;
    }

    public String getPopis() {
        return popis;
    }

    public String getCesta() {
        return cesta;
    }

    public String getSoubor() {
        return soubor;
    }

    public String getUserId() {
        return userId;
    }

    public String getOdevzdano() {
        return odevzdano;
    }

    public String getDatum() {
        return datum;
    }

    public String getMime() {
        return mime;
    }

    public String getSize() {
        return size;
    }

    public String getFileExt() {
        return fileExt;
    }

    public String getUkolId() {
        return ukolId;
    }

    public String getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }
    
    public void setBody(String body){
        this.body = body;
    }
    
    public String[][] getArrayInfo(){
    String[][] info = new String[13][3];
    info[0][0] = "ID";
    info[0][1] = id;
    info[0][2] = "1";
    info[1][0] = "Název";
    info[1][1] = nazev;
    info[1][2] = "1";
    info[2][0] = "Soubor";
    info[2][1] = soubor;
    info[2][2] = "1";
    info[3][0] = "Cesta";
    info[3][1] = cesta;
    info[3][2] = "2";
    info[4][0] = "Popis";
    info[4][1] = popis;
    info[4][2] = "1";
    info[5][0] = "Body";
    info[5][1] = body;
    info[5][2] = "1";
    info[6][0] = "Odevzdano";
    info[6][1] = odevzdano;
    info[6][2] = "9";
    info[7][0] = "Datum";
    info[7][1] = datum;
    info[7][2] = "1";
    info[8][0] = "Student";
    info[8][1] = userId;
    info[8][2] = "8";
    info[9][0] = "Ukol";
    info[9][1] = ukolId;
    info[9][2] = "12";
    info[10][0] = "Přípona";
    info[10][1] = fileExt;
    info[10][2] = "1";
    info[11][0] = "Velikost";
    info[11][1] = size;
    info[11][2] = "1";
    info[12][0] = "MIME";
    info[12][1] = mime;
    info[12][2] = "1";
    return info;}

    /**
     * @return the vysvetleni
     */
    public String getVysvetleni() {
        return vysvetleni;
    }

    /**
     * @param vysvetleni the vysvetleni to set
     */
    public void setVysvetleni(String vysvetleni) {
        this.vysvetleni = vysvetleni;
    }

}
