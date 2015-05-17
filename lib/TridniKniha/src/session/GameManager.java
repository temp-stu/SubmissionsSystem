package session;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import logika.Evidence;
import org.apache.log4j.Logger;
import ui.OknoHandleData;

/**
 * Manažer hry
 *
 * Drží všechny místnosti a jména hráčů na serveru. Poskytuje metody k
 * manipulaci s těmito objekty.
 *
 * @author Bc. Petr Kokeš
 * @version 2.00
 */
public class GameManager {

    private static final Logger LOG = Logger.getLogger(GameManager.class);

    /**
     * Maximální počer místností na serveru
     */
    public static final int MAX_ROOMS = 5;

    /**
     * Maximální počet hráčů v jedné místnosti
     */
    public static final int MAX_PLAYERS = 1;

    /**
     * Výchozí počet sekund, za které dojde k zahájení hry
     */
    public static final int COUNTDOWN = 5;

    /**
     * Všechny místnosti na serveru
     */
    private final Set<Room> rooms;

    /**
     * Jména všech hráčů na serveru - pro kontrolu stejných jmen
     */
    private final Set<String> playerNames;

    private Evidence evidence;

    /**
     * Vytváří manažera hry...
     *
     */
    public GameManager(Evidence evidence) {
//        JOptionPane.showMessageDialog(null, "Server is running!");
        this.rooms = new HashSet<>();
        this.playerNames = new HashSet<>();
        this.evidence = evidence;
    }

    /**
     * Na základě obsahu zprávy provede určitou operaci (potom by to chtělo
     * rozprcat do několik tříd jako v adventuře). Taky bude dobré posunout move
     * nahoru, protože se bude volat nejčastěji.
     *
     * @param line zpráva přijatá od klienta
     * @param player hráč, od kterého zpráva pochází
     */
    public void resolve(String line, Player player) {
        if (!line.equals("clear")) { // nechceme logovat interní příkaz "clear"
            LOG.debug("Preparing to resolve message: " + line);
        }
        String[] contents = line.split("-");

        if (line.matches("login-(.+)")) {
            //přihlášení uživatele, např. "login-vČeLaŘ69"
            //přepošlu celý řádek, protože pokud přijde např "login---", nebylo 
            //by v poli contents nic a padlo by to na NPE
            cmdLogin(line, player);
        } else if (line.equals("clear")) {
            dropPlayer(player, false);
        } else if (contents[0].matches("download")) {
            if (contents[1].matches("homeworks")) {
                if (contents[2].matches("id")) {
                    cmdGetHomeWorksById(player, contents[3]);
                }
                if (contents[2].matches("all")) {
                    cmdGetAllHomeWorksById(player, contents[3]);
                }
            } else {
                if (contents[1].matches("assignments")) {
                    cmdGetAssignment(player);
                }
            }

        } else if (contents[0].matches("solution")) {
            for (int m = 0; m < evidence.userukol.size(); m++) {
                if (evidence.userukol.get(m).getId().equals(contents[1])) {
                    evidence.userukol.get(m).setBody(contents[2]);
                    evidence.userukol.get(m).setVysvetleni(contents[3]);
                    evidence.aktualizujSouborSUKoly();
                    player.getOutput().println("solution-ok");
                }
            }

        } else {
            //nebyla vyhodnocena shoda u žádného z předchozích případů
            LOG.debug("Unknown command: " + line);
            player.getOutput().println("unknown-command");
        }

    }

    private synchronized void cmdGetAllHomeWorksById(Player player, String idUkolu) {
        for (int i = 0; i < evidence.userukol.size(); i++) {
             if (evidence.userukol.get(i).getUkolId().equals(idUkolu)) {
                //System.out.println(evidence.userukol.get(i).getCesta());
                String pathToSolutions = createPath(evidence.userukol.get(i).getCesta());
                // System.out.println(pathToSolutions);
                player.getOutput().println("homework-" + evidence.userukol.get(i).getId() + "-" + evidence.userukol.get(i).getUkolId() + "-" + evidence.userukol.get(i).getUserId() + "-" + evidence.userukol.get(i).getNazev() + "-" + pathToSolutions + "-" + evidence.userukol.get(i).getBody());
            }
        }
        player.getOutput().println("array-end");
    }

    private synchronized void cmdGetAssignment(Player player) {
        for (int i = 0; i < evidence.ukol.size(); i++) {
            player.getOutput().println("assignment-" + evidence.ukol.get(i).getId() + "-" + evidence.ukol.get(i).getTyp() + "-" + evidence.ukol.get(i).getNazev() + "-" + evidence.ukol.get(i).getMaxBody() + "-" + evidence.ukol.get(i).getViditelnost());
        }
        player.getOutput().println("array-end");
    }

    private synchronized void cmdGetHomeWorksById(Player player, String idUkolu) {
        for (int i = 0; i < evidence.userukol.size(); i++) {
            if (evidence.userukol.get(i).getUkolId().equals(idUkolu)) {
                if (evidence.userukol.get(i).getBody().equals("0")) {
                //System.out.println(evidence.userukol.get(i).getCesta());
                String pathToSolutions = createPath(evidence.userukol.get(i).getCesta());
                // System.out.println(pathToSolutions);
                player.getOutput().println("homework-" + evidence.userukol.get(i).getId() + "-" + evidence.userukol.get(i).getUkolId() + "-" + evidence.userukol.get(i).getUserId() + "-" + evidence.userukol.get(i).getNazev() + "-" + pathToSolutions + "-" + evidence.userukol.get(i).getBody());
            }
            }
        }
    }

    private String createPath(String cesta) {
        String uprava = cesta.replaceAll("/data/web/virtuals/[0-9]+/virtual/www/", "");
        uprava = uprava.replaceAll("/var/www/edu-pgm.org/htdocs/", "");
        String localPath = evidence.localPath + "/web/data/FTP/download/" + uprava;
        File myfile = new File(localPath);
        String path = myfile.getAbsolutePath();
        File dir = new File(path.substring(0, path.lastIndexOf(File.separator)));
        return dir.toString();
    }

    /**
     * Přihlašuje hráče k serveru. Zkontroluje, zda tam již není hráč se stejným
     * jménem, pokud tam není, tak ho vytvoří a pošle klientu uprávu o úspěchu
     * pokud tam už je, nevytvoří ho a pošle klientu zprávu o neúspěchu.
     * Kontoluje také platnost jména (vlastně celého příkazu) podle reg. výrazu.
     *
     * @param name jméno hráče - 1. parametr příkazu
     * @param player instance hráče
     */
    private synchronized void cmdLogin(String line, Player player) {
        String result = "login-nok";

        if (line.matches("login-([\\p{L}\\d]+)")) { //původní: "\\p{L}\\d*[\\s\\p{L}\\d]*[\\p{L}\\d]+"
            //pokud jméno hráče odpovídá regexu, proběhne login, jinak je přeskočen
            //a hráči je posláno "login-nok"
            String name = line.split("-")[1]; //teprve tady získávám jméno

            name = name.replaceAll("-", "_").toLowerCase(); //nemělo by se stát, že se do jména dostane pomlčka, ale pro jistotu... :D

            if (player.getName() == null) {
                //jméno hráče je null <=> hráč není přihlášen
                if (!playerNames.contains(name)) {
                    playerNames.add(name);
                    player.setName(name);
                    if (insertPlayer(player)) {
                        result = "login-ok";
                        //pošlu ostatním hráčům v místnosti info o připojení tohoto hráče
//                        player.getRoom().broadcast("joined-"
//                                + player.getName(), player, false);
                    }
                } else {
                    LOG.warn("Player with name " + name
                            + " already exists on server!");
                }
            } else {
                //jméno hráče není null <=> hráč je přihlášen
                LOG.warn("Player " + player.getName()
                        + " tried to log in again using name " + name);
            }
        }

        player.getOutput().println(result);
    }

    /**
     * Vloží hráče do místnosti.
     *
     * Pokud existuje neprázná a neplná místnost, je hráč vložen do ní, pokud
     * taková místnost neexituje a na serveru existuje méně než maximální počet
     * místností, je pro hráče místnost vytvořena. Pokud je na serveru již
     * maximální počet místností, místnost není vytvořena a přihlášení hráče
     * není úspěšné.
     *
     * @param player Hráč, kterého se pokoušíme vložit do místnosti
     * @return true, pokud byl hráč úspěšně vložen do místnosti, jinak false
     */
    private synchronized boolean insertPlayer(Player player) {
        boolean playerInserted = false;
        boolean startGame = false;

        //zkusíme najít neplnou místnost
        for (Room room : rooms) {
            if (!room.isFull()) {
                //máme neplnou a neprázdnou místnost, flusneme ho tam 
                //a nastavíme mu na ni odkaz
                startGame = room.addPlayer(player); //pokud jsme vložením hráče naplnili místnost, nastavíme flag
                player.setRoom(room);
                rooms.add(room);
                playerInserted = true;
                break;
            }
        }

        //nenašli jsme neplnou a neprázdnou místnost, zkusíme ji vytvořit
        if (!playerInserted && rooms.size() != MAX_ROOMS) {
            //není dosažen limit místností, vytvoříme tedy novou a vložíme hráče
            Room room = new Room();
            room.addPlayer(player);
            player.setRoom(room);
            rooms.add(room);
            playerInserted = true;
        }

        //pokud se nám nepodařilo hráče vložit do nějaké místnosti, musíme jeho 
        //záznam také odstranit se seznamu hráčů, aby jeho záznam neblokoval
        //toho jméno ostatním
        if (!playerInserted) {
            playerNames.remove(player.getName());
        }

        //pokud jsme vložením hráče naplnili místnost, odstartujeme hru pomocí Starteru
        if (startGame) {
            LOG.info("Starting game in room "
                    + player.getRoom().getId() + " in " + COUNTDOWN + " seconds");
            new Starter(this, player, COUNTDOWN).start();

            /*FIXME: funguje jen pro dva hráče v místnosti!!
             po zapnutí starteru (= naplnění místnosti) pošleme hráčům info o připojení toho druhého
             ve tvaru userX-jméno, kde X je pořadí hráče*/
            player.getRoom().introducePlayers();
        }

        return playerInserted;
    }

    /**
     * Zahodí veškeré záznamy o hráči. Odebere ho jak z místnosti, tak z množiny
     * jmen na serveru. Volá se, když hráč hru opustí. Pokud je hráč ve fázi,
     * kdy ještě neposlal login-, ale pošle game-end-, není koho vyhazovat,
     * proto příkaz neudělá nic a pokračuje se dále uzavřením připojení.
     *
     * @param player hráč, který opustil hru
     * @param clearTheRoom true, pokud mají být vyhozeni všeichni hráči; false,
     * pokud jen tento hráč
     */
    private synchronized void dropPlayer(Player player, boolean clearTheRoom) {
        if (player.getRoom() != null && player.getName() != null) {
            //pokud takový uživatel na serveru existuje, vyhodíme ho
            if (clearTheRoom) {
                player.getRoom().clearTheRoom(player); //místnost zapomene všechny hráče
            } else {
                player.getRoom().removePlayer(player); //místnost zapomene jen tohoto hráče
            }
            player.setRoom(null); //hráč zapomene svou místnost
            playerNames.remove(player.getName());
            LOG.debug("Dropped player " + player.getName()
                    + " from server");
        } else {
            LOG.debug("Player wasn't logged in, dropping not neccessary!");
        }
    }

}
