package logika;

import java.lang.reflect.Array;

/**
 *Třída datového typuu publikace
 * @author Petr Kokeš
 */
public class Publikace {
    private String id;
    private String nazev;
    private String soubor;
    private String cesta;
    private String url;
    private String subdomain;
    private String viditelnost;
    private String poradi;
    private String idKategorie;
    private String mime;
    private String fileExt;
    private String size;
    private String created; 
    
    public Publikace(String id,String nazev,String soubor,String cesta,String url,
            String subdomain,String viditelnost,String poradi,String idKategorie,String mime,String fileExt,String size,String created){
        this.id = id;
        this.nazev = nazev;
        this.soubor = soubor;
        this.cesta = cesta;
        this.url = url;
        this.subdomain = subdomain;
        this.viditelnost = viditelnost;
        this.poradi = poradi;
        this.idKategorie = idKategorie;
        this.mime = mime;
        this.fileExt = fileExt;
        this.size = size;
        this.created = created;
}
    
    public String getFileExt(){
    return fileExt;}
    
    public String getSize(){
    return size;}
    
    public String getCreated(){
    return created;}
    
    public String getViditelnost(){
    return viditelnost;}
    
    public String getPoradi(){
    return poradi;}
    
    public String getIdKategorie(){
    return idKategorie;}
    
    public String getMime(){
    return mime;}
    
    public String getSubdomain(){
    return subdomain;}
    
    public String getId(){
    return id;}
    
    public String getNazev(){
    return nazev;}
    
    public String getSoubor(){
    return soubor;}
    
    public String getCesta(){
    return cesta;}
    
    public String getUrl(){
    return url;}
    
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
    info[4][0] = "URL";
    info[4][1] = url;
    info[4][2] = "1";
    info[5][0] = "Subdomain";
    info[5][1] = subdomain;
    info[5][2] = "1";
    info[6][0] = "Viditelnost";
    info[6][1] = viditelnost;
    info[6][2] = "9";
    info[7][0] = "Pořadí";
    info[7][1] = poradi;
    info[7][2] = "1";
    info[8][0] = "Kategorie";
    info[8][1] = idKategorie;
    info[8][2] = "10";
    info[9][0] = "MIME";
    info[9][1] = mime;
    info[9][2] = "1";
    info[10][0] = "Přípona";
    info[10][1] = fileExt;
    info[10][2] = "1";
    info[11][0] = "Velikost";
    info[11][1] = size;
    info[11][2] = "1";
    info[12][0] = "Vytvořeno";
    info[12][1] = created;
    info[12][2] = "1";
    return info;}
}
