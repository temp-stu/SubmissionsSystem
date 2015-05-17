package logika;

/**
 *Třída datového typu kategorie publikací
 * @author Petr Kokeš
 */
public class PublikaceKategorie {
    
     private String id;
    private String nazev;
    private String title;
    private String keywords;
    private String description;
    private String poradi;
    private String viditelnost;
    private String url;
    private String subdomain;
    
    public PublikaceKategorie(String id,String nazev,String title,String keywords,String description
    ,String poradi,String viditelnost,String url,String subdomain){
        
    this.id=id;
    this.nazev=nazev;
    this.title=title;
    this.keywords=keywords;
    this.description=description;
    this.poradi=poradi;
    this.viditelnost=viditelnost;
    this.url=url;
    this.subdomain=subdomain;
    }
   
    
    public String getSubdomain() {
        return subdomain;
    }
    
     public String getViditelnost() {
        return viditelnost;
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
        
    
}
