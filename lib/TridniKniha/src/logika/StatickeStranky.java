package logika;

/**
 *Třída datového typu satických stránek
 * @author Petr Kokeš
 */
public class StatickeStranky {
    
     private String id;
    private String nazev;
    private String title;
    private String keywords;
    private String description;
    private String poradi;
    private String viditelnost;
    private String obsah;
    private String url;
    private String urla;
    private String subdomain;
    private String idKategorie;
    private String typMenu;
    
    public StatickeStranky(String id,String nazev,String title,String keywords,String description
    ,String poradi,String viditelnost,String obsah,String url,String urla,String subdomain,String idKategorie,String typMenu){
        
    this.id=id;
    this.nazev=nazev;
    this.title=title;
    this.keywords=keywords;
    this.description=description;
    this.poradi=poradi;
    this.viditelnost=viditelnost;
    this.obsah=obsah;
    this.url=url;
    this.urla=urla;
    this.subdomain=subdomain;
    this.idKategorie=idKategorie;
    this.typMenu=typMenu;
    }
    
    public String getTypMenu() {
        return typMenu;
    }
    
    public String getIdKategorie() {
        return idKategorie;
    }
    
         public String getViditelnost() {
        return viditelnost;
    }
    
    public String getUrla() {
        return urla;
    }
    
    public String getSubdomain() {
        return subdomain;
    }
    
    public String getObsah() {
        return obsah;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getPoradi() {
        return poradi;
    }
    
     public String getKeywords() {
        return keywords;
    }
    
    public String getTitle() {
        return title;
    }
    
        public String getId() {
        return id;
    }
        public String getNazev() {
        return nazev;
    }
        
        public String[][] getArrayInfo(){
    String[][] info = new String[12][2];
    info[0][0] = "ID";
    info[0][1] = id;
    info[0][2] = "1";
    info[1][0] = "Název";
    info[1][1] = nazev;
    info[1][2] = "1";
    info[2][0] = "Title";
    info[2][1] = title;
    info[2][2] = "1";
    info[3][0] = "Klíčová slova";
    info[4][1] = keywords;
    info[4][2] = "1";
    info[5][0] = "Popis";
    info[5][1] = description;
    info[5][2] = "1";
    info[6][0] = "Poradi";
    info[6][1] = poradi;
    info[6][2] = "1";
    info[7][0] = "Viditelnost";
    info[7][1] = viditelnost;
    info[7][2] = "9";
    info[8][0] = "obsah";
    info[8][1] = obsah;
    info[8][2] = "1";
    info[9][0] = "URL";
    info[9][1] = url;
    info[9][2] = "1";
    info[10][0] = "Alternativní URL";
    info[10][1] = urla;
    info[10][2] = "1";
    info[11][0] = "Kategorie";
    info[11][1] = idKategorie;
    info[11][2] = "11";
    info[12][0] = "Typ Menu";
    info[12][1] = typMenu;
    info[12][2] = "13";
    return info;}
        
    
}
