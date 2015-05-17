package logika;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import ui.Gui;

/**
 * Třída PraceSDaty slouží na uchování informací o akcích a studentech
 * 
* @author Petr Kokeš
 * @version 1.00
 */
public class PraceSDaty {

    private Evidence evidence;

    /**
     * Konstruktor dostává evidenci
     *     
     * @param evidence
     */
    public PraceSDaty(Evidence evidence) {
        this.evidence = evidence;
    }

    /**
     * načtení statických stránek
     * @param stat soubor
     * @throws IOException 
     */
    public void nactiStatickeStranky(File stat) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(stat));
        String dataRow = CSVFile.readLine();
        evidence.staticke.clear();
        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String title = null;
            String keywords = null;
            String description = null;
            String poradis = null;
            String viditelnost = null;
            String obsah = null;
            String url = null;
            String urla = null;
            String subdomain = null;
            String idKategorie = null;
            String typMenu = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        title = item;
                        poradi++;
                        break;
                    case 3:
                        keywords = item;
                        poradi++;
                        break;
                    case 4:
                        description = item;
                        poradi++;
                        break;
                    case 5:
                        poradis = item;
                        poradi++;
                        break;
                    case 6:
                        viditelnost = item;
                        poradi++;
                        break;
                    case 7:
                        obsah = item;
                        poradi++;
                        break;
                    case 8:
                        url = item;
                        poradi++;
                        break;
                    case 9:
                        urla = item;
                        poradi++;
                        break;
                    case 10:
                        subdomain = item;
                        poradi++;
                        break;
                    case 11:
                        idKategorie = item;
                        poradi++;
                        break;
                    case 12:
                        typMenu = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            StatickeStranky statickeStranky = new StatickeStranky(id, nazev, title, keywords, description, poradis, viditelnost, obsah, url, urla, subdomain, idKategorie, typMenu);
            evidence.staticke.add(statickeStranky);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

    /**
     * Funkce pro načtení kategorií
     * @param katstat soubor
     * @throws IOException 
     */
    public void nactiKategorieStatic(File katstat) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(katstat));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String icon = null;
            String nazev = null;
            String poradis = null;
            String viditelnost = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        icon = item;
                        poradi++;
                        break;
                    case 2:
                        nazev = item;
                        poradi++;
                        break;
                    case 3:
                        poradis = item;
                        poradi++;
                        break;
                    case 4:
                        viditelnost = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            StatickeStrankyKategorie statickeStrankyKategorie = new StatickeStrankyKategorie(id, icon, nazev, poradis, viditelnost);
            evidence.statickat.add(statickeStrankyKategorie);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

    /**
     * Funkce pro načtení publikací
     * @param pub soubor
     * @throws IOException 
     */
    public void nactiPublikace(File pub) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(pub));
        String dataRow = CSVFile.readLine();
        evidence.publikace.clear();
        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String soubor = null;
            String cesta = null;
            String url = null;
            String subdomain = null;
            String viditelnost = null;
            String poradis = null;
            String idKategorie = null;
            String mime = null;
            String fileExt = null;
            String size = null;
            String created = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        soubor = item;
                        poradi++;
                        break;
                    case 3:
                        cesta = item;
                        poradi++;
                        break;
                    case 4:
                        url = item;
                        poradi++;
                        break;
                    case 5:
                        subdomain = item;
                        poradi++;
                        break;
                    case 6:
                        viditelnost = item;
                        poradi++;
                        break;
                    case 7:
                        poradis = item;
                        poradi++;
                        break;
                    case 8:
                        idKategorie = item;
                        poradi++;
                        break;
                    case 9:
                        mime = item;
                        poradi++;
                        break;
                    case 10:
                        fileExt = item;
                        poradi++;
                        break;
                    case 11:
                        size = item;
                        poradi++;
                        break;
                    case 12:
                        created = item;
                        break;
                    default:
                        break;
                }
            }

            Publikace publikace = new Publikace(id, nazev, soubor, cesta, url, subdomain, viditelnost, poradis, idKategorie, mime, fileExt, size, created);

            evidence.publikace.add(publikace);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

    /**
     * Funkce pro načtení kategorií publikací
     * @param pubkat soubor
     * @throws IOException 
     */
    public void nactiKategoriePublikaci(File pubkat) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(pubkat));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String title = null;
            String keywords = null;
            String description = null;
            String poradis = null;
            String viditelnost = null;
            String url = null;

            String subdomain = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        title = item;
                        poradi++;
                        break;
                    case 3:
                        keywords = item;
                        poradi++;
                        break;
                    case 4:
                        description = item;
                        poradi++;
                        break;
                    case 5:
                        poradis = item;
                        poradi++;
                        break;
                    case 6:
                        viditelnost = item;
                        poradi++;
                        break;
                    case 7:
                        url = item;
                        poradi++;
                        break;
                    case 8:
                        subdomain = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            PublikaceKategorie publikacekategorie = new PublikaceKategorie(id, nazev, title, keywords, description, poradis, viditelnost, url, subdomain);
            evidence.publikacekat.add(publikacekategorie);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

    /**
     * Funkce pro načtení studentů
     * @param studenti soubor
     * @throws IOException 
     */
    public void nactiStudenty(File studenti) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(studenti));
        String dataRow = CSVFile.readLine();
        evidence.student.clear();
        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String username = null;
            String name = null;
            String surname = null;
            String company = null;
            String street = null;
            String streetNumber = null;
            String city = null;
            String discrict = null;
            String zip = null;
            String state = null;
            String email = null;
            String idSkoly = null;
            String idFakulty = null;
            String skupina = null;
            String text = null;
            String hodina = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        username = item;
                        poradi++;
                        break;
                    case 2:
                        name = item;
                        poradi++;
                        break;
                    case 3:
                        surname = item;
                        poradi++;
                        break;
                    case 4:
                        company = item;
                        poradi++;
                        break;
                    case 5:
                        street = item;
                        poradi++;
                        break;
                    case 6:
                        streetNumber = item;
                        poradi++;
                        break;
                    case 7:
                        city = item;
                        poradi++;
                        break;
                    case 8:
                        discrict = item;
                        poradi++;
                        break;
                    case 9:
                        zip = item;
                        poradi++;
                        break;
                    case 10:
                        state = item;
                        poradi++;
                        break;
                    case 11:
                        email = item;
                        poradi++;
                        break;
                    case 12:
                        idSkoly = item;
                        poradi++;
                        break;
                    case 13:
                        idFakulty = item;
                        poradi++;
                        break;
                    case 14:
                        skupina = item;
                        poradi++;
                        break;
                    case 15:
                        text = item;
                        poradi++;
                        break;
                    case 16:
                        hodina = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            Student student = new Student(id, username, name, surname, company, street, streetNumber,
                    city, discrict, zip, state, email, idSkoly, idFakulty, skupina, text,hodina);
            evidence.student.add(student);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

    /**
     * Funkce pro načtení škol
     * @param skoly soubor
     * @throws IOException 
     */
    public void nactiSkoly(File skoly) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(skoly));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String poradis = null;
            String viditelnost = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        poradis = item;
                        poradi++;
                        break;
                    case 3:
                        viditelnost = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            Skola skola = new Skola(id, nazev, poradis, viditelnost);
            evidence.skola.add(skola);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }
/**
 * Funkce pro načtení fakult
 * @param fakulty soubor
 * @throws IOException 
 */
    public void nactiFakulty(File fakulty) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(fakulty));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String poradis = null;
            String viditelnost = null;
            String idSkoly = null;
            String idStatic = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        poradis = item;
                        poradi++;
                        break;
                    case 3:
                        viditelnost = item;
                        poradi++;
                        break;
                    case 4:
                        idSkoly = item;
                        poradi++;
                        break;
                    case 5:
                        idStatic = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            Fakulta fakulta = new Fakulta(id, nazev, poradis, viditelnost, idSkoly, idStatic);
            evidence.fakulta.add(fakulta);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }
/**
 * Funkce pro načtení předmětů
 * @param predmet
 * @throws IOException 
 */
    public void nactiPredmety(File predmet) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(predmet));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String poradis = null;
            String viditelnost = null;
            String idSkoly = null;
            String idFakulty = null;
            String idStatic = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        poradis = item;
                        poradi++;
                        break;
                    case 3:
                        viditelnost = item;
                        poradi++;
                        break;
                    case 4:
                        idSkoly = item;
                        poradi++;
                        break;
                    case 5:
                        idFakulty = item;
                        poradi++;
                        break;
                    case 6:
                        idStatic = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            Predmet predmets = new Predmet(id, nazev, poradis, viditelnost, idSkoly, idFakulty, idStatic);
            evidence.predmety.add(predmets);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }
/**
 * Funkce pro načtení skupin
 * @param skupiny soubor
 * @throws IOException 
 */
    public void nactiSkupiny(File skupiny) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(skupiny));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String idPredmetu = null;
            String idSemestru = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        idPredmetu = item;
                        poradi++;
                        break;
                    case 2:
                        idSemestru = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            Skupina skupina = new Skupina(id, idPredmetu, idSemestru);
            evidence.skupina.add(skupina);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }
/**
 * Funkce pro načtení úkolů
 * @param ukoly soubor
 * @throws IOException 
 */
    public void nactiUkoly(File ukoly) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(ukoly));
        String dataRow = CSVFile.readLine();
        evidence.ukol.clear();
        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String poradis = null;
            String viditelnost = null;
            String idSkoly = null;
            String idFakulty = null;
            String idSkupiny = null;
            String idStatic = null;
            String typ = null;
            String maxbody = null;
            String datumOd = null;
            String datumDo = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        poradis = item;
                        poradi++;
                        break;
                    case 3:
                        viditelnost = item;
                        poradi++;
                        break;
                    case 4:
                        idSkoly = item;
                        poradi++;
                        break;
                    case 5:
                        idFakulty = item;
                        poradi++;
                        break;
                    case 6:
                        idSkupiny = item;
                        poradi++;
                        break;
                    case 7:
                        idStatic = item;
                        poradi++;
                        break;
                    case 8:
                        typ = item;
                        poradi++;
                        break;
                    case 9:
                        maxbody = item;
                        poradi++;
                        break;
                    case 10:
                        datumOd = item;
                        poradi++;
                        break;
                    case 11:
                        datumDo = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            Ukol ukol = new Ukol(id, nazev, poradis, viditelnost, idSkoly, idFakulty, idSkupiny, idStatic, typ, maxbody, datumOd, datumDo);
            evidence.ukol.add(ukol);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

    /**
     * Funkce pro načtení odevzdaných úkolů
     * @param userukoly soubor
     * @throws IOException 
     */
    public void nactiUserUkoly(File userukoly) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(userukoly));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;
            String id = null;
            String nazev = null;
            String soubor = null;
            String cesta = null;
            String popis = null;
            String body = null;
            String odevzdano = null;
            String datum = null;
            String userId = null;
            String ukolId = null;
            String fileExt = null;
            String size = null;
            String mime = null;

            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        id = item;
                        poradi++;
                        break;
                    case 1:
                        nazev = item;
                        poradi++;
                        break;
                    case 2:
                        soubor = item;
                        poradi++;
                        break;
                    case 3:
                        cesta = item;
                        poradi++;
                        break;
                    case 4:
                        popis = item;
                        poradi++;
                        break;
                    case 5:
                        body = item;
                        poradi++;
                        break;
                    case 6:
                        odevzdano = item;
                        poradi++;
                        break;
                    case 7:
                        datum = item;
                        poradi++;
                        break;
                    case 8:
                        userId = item;
                        poradi++;
                        break;
                    case 9:
                        ukolId = item;
                        poradi++;
                        break;
                    case 10:
                        fileExt = item;
                        poradi++;
                        break;
                    case 11:
                        size = item;
                        poradi++;
                        break;
                    case 12:
                        mime = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            UserUkol userUkol = new UserUkol(id, nazev, soubor, cesta, popis, body, odevzdano, datum, userId, ukolId, fileExt, size, mime);
            evidence.userukol.add(userUkol);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();
    }

/**
 * Funkce pro uložení textu do souborry
 * @param file soubor
 * @param retezec csv string
 * @return 
 */
    public boolean ulozDoSouboru(File file, String retezec) {
        BufferedWriter logger = null;
        try {
            logger = new BufferedWriter(new FileWriter(file, true));
            logger.write(retezec);
            logger.newLine();

        } catch (IOException e) {
            System.out.println("Soubor nebyl nalezen.");
            return false;
        } finally {
            if (logger != null) {
                try {
                    logger.close();
                } catch (IOException ex) {
                    System.out.println("Chyba v uzavirani souboru");
                }
            }
            return true;
        }
    }
/**
 * Funkce pro načtení server dat
 * @param f soubor
 * @throws IOException 
 */
    public void getServerData(File f) throws IOException {

        BufferedReader CSVFile = new BufferedReader(new FileReader(f));
        String dataRow = CSVFile.readLine();

        while (dataRow != null) {
            int poradi = 0;

            String server = null;
            String username = null;
            String pass = null;
            String protokol = null;
            String[] dataArray = dataRow.split(";");
            for (String item : dataArray) {
                switch (poradi) {
                    case 0:
                        server = item;
                        poradi++;
                        break;
                    case 1:
                        username = item;
                        poradi++;
                        break;
                    case 2:
                        pass = item;
                        poradi++;
                        break;
                    case 3:
                        protokol = item;
                        poradi++;
                        break;
                    default:
                        break;
                }
            }
            FtpLoginData ftpLoginData = new FtpLoginData(server, username, pass, protokol);
            evidence.ftpLoginData.add(ftpLoginData);
            dataRow = CSVFile.readLine();
        }
        CSVFile.close();

    }
/**
 * Vymazání libovolného souboru
 * @param f soubor
 */
    public void delete(File f) {
        f.delete();
    }
/**
 * Uložení server dat
 * @param f soubor
 * @param retezec csv string
 * @return 
 */
    public boolean ulozeniFtpLoginData(File f, String retezec) {
        BufferedWriter logger = null;
        try {
            logger = new BufferedWriter(new FileWriter(f, true));
            logger.write(retezec);
            logger.newLine();

        } catch (IOException e) {
            System.out.println("Soubor nebyl nalezen.");
            return false;
        } finally {
            if (logger != null) {
                try {
                    logger.close();
                } catch (IOException ex) {
                    System.out.println("Chyba v uzavirani souboru");
                }
            }
        }
        return true;

    }


    /**
     * Hlavní funkce která z patřečný array dle mode předělá na CSV soubor
     * @param mode mode pro jednotlivé záložky
     * @param readmode zda se edituje či ukládá
     * @param csvString nový text
     * @param toDelete jaké položka ze seznamu se edituje
     * @throws IOException 
     */
    public void aktualizujMainData(int mode,int readmode,String csvString,int toDelete) throws IOException{
        
        if(mode == 1){
            if(readmode == 2){            
            evidence.pub.delete();
            evidence.publikace.remove(toDelete);
            for(int i = 0;i<evidence.publikace.size();i++){
            String csvUpdate = evidence.publikace.get(i).getId()+";"+evidence.publikace.get(i).getNazev()+";"+evidence.publikace.get(i).getSoubor()+";"+evidence.publikace.get(i).getCesta()+";"+evidence.publikace.get(i).getUrl()+";"+evidence.publikace.get(i).getSubdomain()+";"+evidence.publikace.get(i).getViditelnost()+";"+evidence.publikace.get(i).getPoradi()+";"+evidence.publikace.get(i).getIdKategorie()+";"+evidence.publikace.get(i).getMime()+";"+evidence.publikace.get(i).getFileExt()+";"+evidence.publikace.get(i).getSize()+";"+evidence.publikace.get(i).getCreated();
            ulozDoSouboru(evidence.pub,csvUpdate);
            }}
            ulozDoSouboru(evidence.pub,csvString);
            nactiPublikace(evidence.pub);
            
        }
        
        if(mode == 2){
            if(readmode == 2){            
            evidence.ukoly.delete();
            evidence.ukol.remove(toDelete);
            for(int i = 0;i<evidence.ukol.size();i++){
            String csvUpdate = evidence.ukol.get(i).getId()+";"+evidence.ukol.get(i).getNazev()+";"+evidence.ukol.get(i).getViditelnost()+";"+evidence.ukol.get(i).getPoradi()+";"+evidence.ukol.get(i).getIdSkoly()+";"+evidence.ukol.get(i).getIdFakulty()+";"+evidence.ukol.get(i).getIdSkupiny()+";"+evidence.ukol.get(i).getIdStatic()+";"+evidence.ukol.get(i).getTyp()+";"+evidence.ukol.get(i).getMaxBody()+";"+evidence.ukol.get(i).getDatumOd()+";"+evidence.ukol.get(i).getDatumDo();
            ulozDoSouboru(evidence.ukoly,csvUpdate);
            }}
            ulozDoSouboru(evidence.ukoly,csvString);
            nactiUkoly(evidence.ukoly);

        } 
        
            if(mode == 3){
            if(readmode == 2){            
            evidence.studenti.delete();
            evidence.student.remove(toDelete);
            for(int i = 0;i<evidence.student.size();i++){
            String csvUpdate = evidence.student.get(i).getId()+";"+evidence.student.get(i).getUsername()+";"+evidence.student.get(i).getName()+";"+evidence.student.get(i).getSurname()+";"+evidence.student.get(i).getCompany()+";"+evidence.student.get(i).getStreet()+";"+evidence.student.get(i).getStreetNumber()+";"+evidence.student.get(i).getCity()+";"+evidence.student.get(i).getDiscrict()+";"+evidence.student.get(i).getZip()+";"+evidence.student.get(i).getState()+";"+evidence.student.get(i).getEmail()+";"+evidence.student.get(i).getIdSkoly()+";"+evidence.student.get(i).getIdFakulty()+";"+evidence.student.get(i).getHodina();
            ulozDoSouboru(evidence.studenti,csvUpdate);
            }}
            ulozDoSouboru(evidence.studenti,csvString);
            nactiStudenty(evidence.studenti);
        }

            if(mode == 4){
            if(readmode == 2){            
            evidence.stat.delete();
            evidence.staticke.remove(toDelete);
            for(int i = 0;i<evidence.staticke.size();i++){
            String csvUpdate = evidence.staticke.get(i).getId()+";"+evidence.staticke.get(i).getNazev()+";"+evidence.staticke.get(i).getTitle()+";"+evidence.staticke.get(i).getKeywords()+";"+evidence.staticke.get(i).getDescription()+";"+evidence.staticke.get(i).getPoradi()+";"+evidence.staticke.get(i).getViditelnost()+";"+evidence.staticke.get(i).getObsah()+";"+evidence.staticke.get(i).getUrl()+";"+evidence.staticke.get(i).getUrla()+";"+evidence.staticke.get(i).getSubdomain()+";"+evidence.staticke.get(i).getIdKategorie()+";"+evidence.staticke.get(i).getTypMenu();
            ulozDoSouboru(evidence.stat,csvUpdate);
            }}
            ulozDoSouboru(evidence.stat,csvString);
            nactiStatickeStranky(evidence.stat);
        }
          if(mode == 5){
            if(readmode == 2){            
            evidence.userukoly.delete();
            evidence.userukol.remove(toDelete);
            for(int i = 0;i<evidence.userukol.size();i++){
            String csvUpdate = evidence.userukol.get(i).getId()+";"+evidence.userukol.get(i).getNazev()+";"+evidence.userukol.get(i).getSoubor()+";"+evidence.userukol.get(i).getCesta()+";"+evidence.userukol.get(i).getPopis()+";"+evidence.userukol.get(i).getBody()+";"+evidence.userukol.get(i).getOdevzdano()+";"+evidence.userukol.get(i).getDatum()+";"+evidence.userukol.get(i).getUserId()+";"+evidence.userukol.get(i).getUkolId()+";"+evidence.userukol.get(i).getFileExt()+";"+evidence.userukol.get(i).getSize()+";"+evidence.userukol.get(i).getMime();
            ulozDoSouboru(evidence.userukoly,csvUpdate);
            }}
            ulozDoSouboru(evidence.userukoly,csvString);
            nactiStatickeStranky(evidence.userukoly);
        }
      
        
    }
    
   public void aktualizujUkoly(){
       evidence.userukoly.delete();
   for(int i = 0;i<evidence.userukol.size();i++){
            String csvUpdate = evidence.userukol.get(i).getId()+";"+evidence.userukol.get(i).getNazev()+";"+evidence.userukol.get(i).getSoubor()+";"+evidence.userukol.get(i).getCesta()+";"+evidence.userukol.get(i).getPopis()+";"+evidence.userukol.get(i).getBody()+";"+evidence.userukol.get(i).getOdevzdano()+";"+evidence.userukol.get(i).getDatum()+";"+evidence.userukol.get(i).getUserId()+";"+evidence.userukol.get(i).getUkolId()+";"+evidence.userukol.get(i).getFileExt()+";"+evidence.userukol.get(i).getSize()+";"+evidence.userukol.get(i).getMime();
            ulozDoSouboru(evidence.userukoly,csvUpdate);
            }
   }

}
