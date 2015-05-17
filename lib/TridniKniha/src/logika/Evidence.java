package logika;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída Evidence vytváří evidenci na uchování dat
 * 
* @author Petr Kokeš
 * @version 2.00
 */
public class Evidence {
    
    public ArrayList<FtpLoginData> ftpLoginData;
    private PraceSDaty archiv;
    public ArrayList<StatickeStranky> staticke;
    public ArrayList<StatickeStrankyKategorie> statickat;
    public ArrayList<Publikace> publikace;
    public ArrayList<PublikaceKategorie> publikacekat;
    public ArrayList<Student> student;
    public ArrayList<Skola> skola;
    public ArrayList<Fakulta> fakulta;
    public ArrayList<Predmet> predmety;
    public ArrayList<Skupina> skupina;
    public ArrayList<Ukol> ukol;
    public ArrayList<UserUkol> userukol;
    public File ftpLogin;
    public File stat;
    public File katstat;
    public File pub;
    public File pubkat;
    public File studenti;
    public File skoly;
    public File fakulty;
    public File predmet;
    public File skupiny;
    public File ukoly;
    public File userukoly;
    public String localPath;
    

    /**
     * Konstruktor vytvoří prázdnou evidenci.
     */
    public Evidence(String localPath) throws Exception {        

        this.localPath = localPath;
        
        ftpLogin = new File(localPath+"\\web\\data\\main\\FtpData.csv");        
        stat = new File(localPath+"\\web\\data\\main\\menu.csv");
        katstat = new File(localPath+"\\web\\data\\main\\kategoriemenu.csv");
        pub = new File(localPath+"\\web\\data\\main\\publikace.csv");
        pubkat = new File(localPath+"\\web\\data\\main\\publikacekategorie.csv");
        studenti = new File(localPath+"\\web\\data\\main\\studenti.csv");
        skoly = new File(localPath+"\\web\\data\\main\\skoly.csv");
        fakulty = new File(localPath+"\\web\\data\\main\\fakulty.csv");
        predmet = new File(localPath+"\\web\\data\\main\\predmet.csv");
        skupiny = new File(localPath+"\\web\\data\\main\\skupinysemestrpredmet.csv");
        ukoly = new File(localPath+"\\web\\data\\main\\ukoly.csv");
        userukoly = new File(localPath+"\\web\\data\\main\\userukoly.csv");
        
        
        staticke = new ArrayList<>();
        statickat = new ArrayList<>();
        publikace = new ArrayList<>();
        publikacekat = new ArrayList<>();
        student = new ArrayList<>();
        skola = new ArrayList<>();
        fakulta = new ArrayList<>();
        predmety = new ArrayList<>();
        skupina = new ArrayList<>();
        ukol = new ArrayList<>();
        userukol = new ArrayList<>();        
        ftpLoginData = new ArrayList<>();
        archiv = new PraceSDaty(this);
        
        //zakládání nových souborů při instalaci
        if (!ftpLogin.exists()){
           archiv.ulozeniFtpLoginData(ftpLogin,"edu-pgm.org;external;external15;SSL");
           archiv.ulozeniFtpLoginData(ftpLogin,"edu-pgm.org;admin;madPC2014;none");
        }
        if(!stat.exists()){archiv.ulozDoSouboru(stat,"18;pecinovsky.cz;pecinovsky.cz;pecinovsky.cz;pecinovsky.cz;1;1;<table border=\"0\" style=\"background:#FFFFCC; width:750px\">	<tbody>		<tr>			<td>			<p><img alt=\"pecinovsky.cz\" src=\"www/files/obrazky/static/background01.jpg\" style=\"height:100px; width:600px\" /></p>			</td>		</tr>		<tr>			<td>			<hr /></td>		</tr>	</tbody></table><table border=\"0\" style=\"width:750px\"><tbody><tr><td><table align=\"center\" border=\"0\"><tbody><tr><td><p><a href=\"http://rudolf.cool-it-1.cz/index.htm\" target=\"_self\">RUDOLF PECINOVSK&Yacute;</a> - EDU expert, autor poč&iacute;tačov&yacute;ch př&iacute;ruček</p></td></tr><tr><td><p><a href=\"http://josef.pecinovsky.cz/index.htm\" target=\"_self\">JOSEF PECINOVSK&Yacute;</a> - spisovatel</p></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" style=\"width:750px\"><tbody><tr><td><hr /></td></tr><tr><td><p>&nbsp;</p></td></tr></tbody></table><table border=\"0\" style=\"width:750px\"><tbody><tr><td><p>optimalizov&aacute;no pro 800x600 24bit truecolor</p>			</td>		</tr>		<tr><td><p>(c) 2001-2009</p></td></tr></tbody></table>;index;index.htm;null;3;0");}
        if(!katstat.exists()){archiv.ulozDoSouboru(katstat,"1;fa-files-o;Basic;1;0;");}
        if(!pub.exists()){archiv.ulozDoSouboru(pub,"1;Testovací soubor;background01.jpg;/data/web/virtuals/55455/virtual/www/www/files/publikace/background01.jpg;neco/silenyho/hodne/daleko/ucese.jpg;publikace;1;1;1;image/jpeg;.jpg;43;2014-05-02 00:00:00");}
        if(!pubkat.exists()){archiv.ulozDoSouboru(pubkat,"1;E-learning;E-learning;E-learning, knihy, eknih;E-learning;1;1;elearning;publikace");}
        if(!studenti.exists()){archiv.ulozDoSouboru(studenti,"2;student;test;test;;;0;;;;;test@test.cz;1;1;;;");}
        if(!skoly.exists()){archiv.ulozDoSouboru(skoly,"3;žádná;0;0");}
        if(!fakulty.exists()){archiv.ulozDoSouboru(fakulty,"4;Fakulta informatiky a statistiky;1;4;1;24");}
        if(!predmet.exists()){archiv.ulozDoSouboru(predmet,"1;4IT101 – Programování v Javě;1;1;1;4;20");}
        if(!skupiny.exists()){archiv.ulozDoSouboru(skupiny,"1;1;1");}
        if(!ukoly.exists()){archiv.ulozDoSouboru(ukoly,"1;Vstupní test;1;1;1;4;1;17;anonymní test;0;2014-05-03;2014-05-13");}
        if(!userukoly.exists()){archiv.ulozDoSouboru(userukoly,"3;Test;pear.png;/data/web/virtuals/55455/virtual/www/www/files/ukoly/student/vstupntest/2014-05-04/pear.png;;1;1;2014-05-04 22:33:33;2;3;.png;1.62;image/png");}
     

        
        //samotné načítání
        archiv.nactiStatickeStranky(stat);
        archiv.nactiKategorieStatic(katstat);
        archiv.nactiPublikace(pub);
        archiv.nactiKategoriePublikaci(pubkat);
        archiv.nactiStudenty(studenti);
        archiv.nactiSkoly(skoly);
        archiv.nactiFakulty(fakulty);
        archiv.nactiPredmety(predmet);
        archiv.nactiSkupiny(skupiny);
        archiv.nactiUkoly(ukoly);
        archiv.nactiUserUkoly(userukoly);
        archiv.getServerData(ftpLogin);

    }
    
    public void aktualizujSouborSUKoly(){
    archiv.aktualizujUkoly();
    }



    /**
     * Metoda vrací archiv dat
     */
    public PraceSDaty getArchiv() {
        return archiv;
    }
}
