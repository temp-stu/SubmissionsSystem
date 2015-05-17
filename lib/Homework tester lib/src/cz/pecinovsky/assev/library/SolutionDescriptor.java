/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.pecinovsky.assev.library;



/*******************************************************************************
 * Instance třídy {@code SolutionDescriptor} představují ...
 * Instances of class {@code SolutionDescriptor} represent ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class SolutionDescriptor extends Descriptor
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    public final String assignmentID;
    public final String userID;
    public final String solutionPath;
    public final String points;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     * @param internalID
     * @param assignmentID
     * @param userID
     * @param title
     * @param solutionPath
     * @param points
     */
    public SolutionDescriptor(String internalID,
                              String assignmentID,
                              String userID,
                              String title,
                              String solutionPath,
                              String points)
    {
        super(internalID, title);
        this.assignmentID = assignmentID;
        this.userID       = userID;
        this.solutionPath = solutionPath;
        this.points = points;
    }


}
