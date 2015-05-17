/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.pecinovsky.assev.library;



/*******************************************************************************
 * Instance třídy {@code AssignmentDescriptor} představují ...
 * Instances of class {@code AssignmentDescriptor} represent ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class AssignmentDescriptor extends Descriptor
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
    public final String maxPoints;
    public final String visibility;
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     * @param internalID
     * @param assignmentID
     * @param title
     */
    public AssignmentDescriptor(String internalID,
                                String assignmentID,
                                String title,
                                String maxPoints,
                                String visibility)
    {
        super(internalID, title);
        this.assignmentID = assignmentID;
        this.maxPoints = maxPoints;
        this.visibility = visibility;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
