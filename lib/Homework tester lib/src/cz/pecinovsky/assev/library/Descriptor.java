/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.pecinovsky.assev.library;



/*******************************************************************************
 * Instance třídy {@code Descriptor} představují ...
 * Instances of class {@code Descriptor} represent ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Descriptor
{
    public final String internalID;
    public final String title;

    /**
     * Funkce pro nastavování deskriptoru
     * @param internalID
     * @param title 
     */
    public Descriptor(String internalID, String title)
    {
        this.internalID = internalID;
        this.title      = title;
    }

}
