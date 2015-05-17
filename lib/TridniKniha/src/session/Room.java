package session;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author Bc. Petr Kokeš
 */
class Room {

    private static final Logger LOG = Logger.getLogger(Room.class);

    /**
     * Číselný identifikátor místnosti, jedná se o systémový čas v ms
     */
    private final long Id;

    /**
     * Množina hráčů v této místnosti
     */
    private final Set<Player> players;

    /**
     * Indikuje, zda hra v této místnosti právě běží
     */
    private boolean running;

    /**
     * Místnost, která obsahuje hráče. Jde v podstatě o jednu z několika her,
     * které mohou běžet na serveru zároveň.
     */
    Room() {
        this.running = false;
        this.Id = System.currentTimeMillis();
        this.players = new HashSet<>();
        LOG.info("Created a new room " + Id);
    }

    /**
     * Vrací ID místnosti
     *
     * @return ID místnosti
     */
    long getId() {
        return Id;
    }

    /**
     * Přidá do místnosti nového hráče.
     *
     * @param player hráč vkládáný do místnosti
     */
    synchronized boolean addPlayer(Player player) {
        boolean startGame = false;
        if (!isFull()) {
            players.add(player);
            LOG.info("Player " + player.getName()
                    + " has entered the room " + Id);
            if (isFull()) {
                //pokud jsme přidáním hráče místnost zaplnili, řekneme manažeru, aby odstartoval hru
                startGame = true;
            }
        } else {
            LOG.error("Unable to add player " + player.getName()
                    + " , the room " + Id + " is full!");
        }
        return startGame;
    }

    /**
     * Odebere hráče z místnosti.
     *
     * @param player hráč odebíraný z místnosti
     */
    synchronized void removePlayer(Player player) {
        players.remove(player);
        LOG.info("Player " + player.getName()
                + " has left the room " + Id);
    }

    /**
     * Odebere všechny hráče z hráčovy místonosti místnosti.
     *
     * @param player hráč, jehož místnost bude vyčištěna
     */
    synchronized void clearTheRoom(Player player) {
        for (Player plr : player.getRoom().players) {
            players.remove(plr);
            LOG.info("Player " + player.getName()
                    + " has left the room " + Id);
        }
    }

    /**
     * Vrací true, pokud je počet hráčů v místnosti roven maximálnímu povolenému
     * počtu
     *
     * @return true, pokud je místnost plná, jinak false
     */
    synchronized boolean isFull() {
        return players.size() == GameManager.MAX_PLAYERS;
    }

    /**
     * Pošle přijatou zprávu hráčům v této místnosti. V záslisnoti na parametru
     * includeSender ji pošle všem včetně nebo mimo odesílatele.
     *
     * @param message odesílaná zpráva
     * @param player hráč, který zprávu odesílá
     * @param includeSender true, pokud má být zpráva poslána i odesílateli,
     * jinak false
     */
    synchronized void broadcast(String message, Player sender, boolean includeSender) {
        for (Player player : players) {
            if (includeSender) {
                //pokud má zpráva přijít i odesílateli (např v případě game-start)
                player.sendMessage(message);
            } else {
                //zpráva má přijít jen ostatním hráčům (např v případě move-...)
                if (!player.equals(sender)) {
                    player.sendMessage(message);
                }
            }
        }

        LOG.debug("Broadcasted message " + message + " to room "
                + Id);
    }

//    /**
//     * Všem hráčům v místnosti včetně odesílatele pošle zprávu o startu hry
//     *
//     * @param player hráč, který vstupem do místnosti odstartoval hru
//     */
//    void sendStart(Player player) {
//        player.getRoom().broadcast("game-start", player, true);
//    }
    /**
     * Nastavuje stav hry - zda běží nebo ne
     *
     * @param running true, pokud hra běží, jinak false
     */
    void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Vrací stav hry - zda běží nebo ne
     *
     * @return true, pokud hra běží, jinak false
     */
    boolean isRunning() {
        return running;
    }

    /**
     * Každý hráč pošle všem ostatním své jméno.
     *
     * Tvar userX-jmeno, kde X je pořadí hráče. Server takto určí, kdo je který
     * hráč na herní ploše.
     */
    void introducePlayers() {
        int i = 0;
        for (Player player : players) {
            i++;
            player.getRoom().broadcast("user" + i + "-"
                    + player.getName(), player, false);
        }

    }

    /**
     * Vrací true, pokud v místonosti exituje hráč s přijatým jménem
     *
     * @param name přijaté jméno - dotaz na existenci hráče
     * @return true pokud je v místonsti takový hráč, false jinak
     */
    boolean containsName(String name) {
        boolean result = false;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }
}
