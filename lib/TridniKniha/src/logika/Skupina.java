package logika;

/**
 *Třída datového typu škola
 * @author Petr Kokeš
 */
public class Skupina {

    private String id;
    private String idPredmetu;
    private String idSemestru;

    public Skupina(String id, String idPredmetu, String idSemestru) {

        this.id = id;
        this.idPredmetu = idPredmetu;
        this.idSemestru = idSemestru;
    }

    public String getId() {
        return id;
    }

    public String getIdPredmetu() {
        return idPredmetu;
    }
    
    
    public String getIdSemestru() {
        return idSemestru;
    }

}
