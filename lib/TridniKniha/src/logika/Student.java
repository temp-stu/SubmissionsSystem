package logika;

/**
 *Třída datového typu student
 * @author Petr Kokeš
 */
public class Student {
    
    private String id;
    private String username;
    private String name;
    private String surname;
    private String company;
    private String street;
    private String streetNumber;
    private String city;
    private String discrict;
    private String zip;
    private String state;
    private String email;
    private String idSkoly;
    private String idFakulty;
    private String skupina;
    private String text;
    private String hodina;
    
    public Student(String id,String username,String name,String surname,String company,String street,
            String streetNumber,String city,String discrict,String zip,String state,String email,String idSkoly
    ,String idFakulty,String skupina,String text,String hodina){
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.company = company;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.discrict = discrict;
        this.zip = zip;
        this.state = state;
        this.email = email;
        this.idSkoly = idSkoly;
        this.idFakulty = idFakulty;
        this.skupina = skupina;
        this.text = text;
        this.hodina = hodina;
    }
    public String getHodina(){
    return hodina;}
    
    public String getIdFakulty(){
    return idFakulty;}
    
    public String getSkupina(){
    return skupina;}
    
    public String getText(){
    return text;}
    
    public String getState(){
    return state;}
    
    public String getEmail(){
    return email;}
    
    public String getIdSkoly(){
    return idSkoly;}
    
    public String getStreet(){
    return street;}
    
    public String getStreetNumber(){
    return streetNumber;}
    
    public String getCity(){
    return city;}
    
    public String getDiscrict(){
    return discrict;}
    
    public String getZip(){
    return zip;}
    
    public String getCompany(){
    return company;}
    
    public String getUsername(){
    return username;}
    
    public String getName(){
    return name;}
    
    public String getSurname(){
    return surname;}
    
    public String getId(){
    return id;}
    
    public String[][] getArrayInfo(){
    String[][] info = new String[13][3];
    info[0][0] = "ID";
    info[0][1] = id;
    info[0][2] = "1";
    info[1][0] = "Přihlašovací jméno";
    info[1][1] = username;
    info[1][2] = "1";
    info[2][0] = "Jméno";
    info[2][1] = name;
    info[2][2] = "1";
    info[3][0] = "Společnost";
    info[3][1] = company;
    info[3][2] = "1";
    info[4][0] = "Ulice";
    info[4][1] = street;
    info[4][2] = "1";
    info[5][0] = "Číslo popisné";
    info[5][1] = streetNumber;
    info[5][2] = "1";
    info[6][0] = "Město";
    info[6][1] = city;
    info[6][2] = "1";
    info[7][0] = "Kraj";
    info[7][1] = discrict;
    info[7][2] = "1";
    info[8][0] = "PSČ";
    info[8][1] = zip;
    info[8][2] = "1";
    info[9][0] = "Stát";
    info[9][1] = state;
    info[9][2] = "1";
    info[10][0] = "E-mail";
    info[10][1] = email;
    info[10][2] = "1";
    info[11][0] = "Škola";
    info[11][1] = idSkoly;
    info[11][2] = "3";
    info[12][0] = "Fakulta";
    info[12][1] = idFakulty;
    info[12][2] = "3";
    info[12][0] = "Hodina";
    info[12][1] = hodina;
    info[12][2] = "1";
    return info;
}
}