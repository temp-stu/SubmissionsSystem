package session;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 * Hráč
 *
 * Drží informace o připojeném hráči
 *
 * @author Bc. Petr Kokeš
 * @version 2.00
 */
public class Player {

    private static final Logger LOG = Logger.getLogger(Player.class);

    /**
     * Hráčův vstup
     */
    private final PrintWriter output;

    /**
     * Hráčův výstup
     */
    private final BufferedReader input;

    /**
     * Jméno hráče
     */
    private String name;

    /**
     * Místnost, ve které se hráč nachází
     */
    private Room room;

    /**
     * Vytvoří hráče pouze s jeho vystupy a výstupy. Instance je vytvářena hned
     * po připojení hráče ještě před kontrolou jména
     *
     * @param output to, co odesílá zprávy hráči na jeho vstup
     * @param input to, co přijímá zprávy od hráče z jeho výstupu
     */
    public Player(PrintWriter output, BufferedReader input) {
        this.output = output;
        this.input = input;
        this.name = null;
    }

    /**
     * Pošle zprávu tomuto hráči
     *
     * @param message zpráva pro tohoto hráče
     */
    void sendMessage(String message) {
        output.println(message);
        LOG.debug("Sent message " + message + " to player "
                + name + " in room " + room.getId());
    }

    /**
     * Nastaví jméno hráče
     *
     * @param name jméno hráče
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Přířadí hráči místnost
     *
     * @param room
     */
    void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Vrátí jméno hráče
     *
     * @return name jméno hráče
     */
    String getName() {
        return name;
    }

    /**
     * Vrátí místnost, ve které hráč je
     *
     * @return room hráčova místnost
     */
    Room getRoom() {
        return room;
    }

    /**
     * Vrací výstupní proud = hráčův vstup
     *
     * @return výstupní proud
     */
    public PrintWriter getOutput() {
        return output;
    }

    /**
     * Vrací vstupní proud = hráčův výstup
     *
     * @return vstupní proud
     */
    public BufferedReader getInput() {
        return input;
    }

    @Override
    public int hashCode() {
        int hash = 7;
//        hash = 37 * hash + Objects.hashCode(this.output);
//        hash = 37 * hash + Objects.hashCode(this.input);
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Dva hráči jsou stejní, pokud mají stejné jméno
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
//        if (!Objects.equals(this.output, other.output)) {
//            return false;
//        }
//        if (!Objects.equals(this.input, other.input)) {
//            return false;
//        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
