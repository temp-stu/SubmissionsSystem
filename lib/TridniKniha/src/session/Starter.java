package session;

import org.apache.log4j.Logger;

/**
 * Starter slouží k automatickému odstartování hry po určitém čase poté, co byla
 * místnost zaplněna. Pokud někdo během tohoto procesu z místnosti odejde, hra
 * nebude odstartována.
 *
 * @author Bc. Petr Kokeš
 * @version 2.00
 */
public class Starter extends Thread {

    private static final Logger LOG = Logger.getLogger(Starter.class);

    private final Player player;
    private final GameManager gameManager;
    private final int seconds;

    /**
     * Vytvoří souštěč, který v novém vlákně umožňuje spustit odpočet zahájení
     * hry. Po skončení odpočtu zavolá metogu game manageru, které hru
     * odsatartuje.
     *
     * @param gameManager Instance game managera
     * @param player Hráč, který hru ostartoval naplněním místnosti
     * @param seconds Počet sekund do zahájení hry
     */
    public Starter(GameManager gameManager, Player player, int seconds) {
        super("StarterThread");
        this.gameManager = gameManager;
        this.player = player;
        this.seconds = seconds;
    }

    /**
     * Zapne odpočet na konci kterého ověří, zda je místnost plná, pokud ano,
     * odstartuje hru. Pokud ne, hru neodstartuje.
     */
    @Override
    public void run() {
        try {
            LOG.info("Starting game in room " + player.getRoom().getId()
                    + " in " + seconds + " seconds");
            Thread.sleep(seconds * 1000);
            if (player.getRoom() != null && player.getRoom().isFull()) {
               
                LOG.info("Session in room " + player.getRoom().getId()
                        + " has started");
            } else {
                LOG.warn("Unable to start the session, someone has left!");
            }
        } catch (InterruptedException ex) {
            LOG.error("Starter interrupted!", ex);
        } catch (IllegalArgumentException ill) {
            LOG.error("Wrong number of seconds: " + seconds, ill);
        }
    }
}
