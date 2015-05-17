package logika;

/**
 *Třída datového typu loginů na servery
 * @author Petr Kokeš
 */
public class FtpLoginData {
    private String server;
    private String user;
    private String pass;
    private String protokol;
    
    /**
    *Konstruktor třídy 
    */
     public FtpLoginData(String server, String user, String pass, String protokol) {
         
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.protokol = protokol;
     
     }
     /**
      * Metoda pro vrátecní url server
      * @return string
      */
     public String getServer() {
        return server;
    }

    /**
     * Metoda vracíživatelské jméno k připojení na FTP
     */
    public String getUser() {
        return user;
    }

    /**
     * Metoda vrací heslo k připojení na FTP
     */
    public String getPass() {
        return pass;
    }

    /**
     * Metoda vrací přez který potokol se má FTP připojit
     */
    public String getProtokol() {
        return protokol;
    }
}