/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.pecinovsky.assev.library;

import cz.pecinovsky.assev.library.connection.ServerConnection;
import cz.pecinovsky.assev.library.ui.Evidence;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * *****************************************************************************
 * Instance třídy {@code LibraryContext} představují ... Instances of class
 * {@code LibraryContext} represent ...
 *
 * @author Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class LibraryContext {
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final LibraryContext SINGLETON = new LibraryContext();
    private Evidence evidence ;
    private ServerConnection connection;

    /**
     * *************************************************************************
     *
     */
    public static LibraryContext getInstance() {
        return SINGLETON;
    }
    
    public LibraryContext MHTest() {
        return SINGLETON;
    }

    /**
     * *************************************************************************
     *
     */
    private LibraryContext() {
        evidence = new Evidence();
        connection = new ServerConnection(evidence, true);
    }

    /**
     * *************************************************************************
     *
     * @return Kolekce deskriptorů zadání
     */
    public synchronized ArrayList<AssignmentDescriptor> getAssignmentDescriptors() {
       // if(connection.initFailed == true){connection = new ServerConnection(evidence, true);}
        evidence.assignment.clear();
        evidence.transmiting = true;
        connection.sendMessage("download-assignments");
        while (evidence.transmiting) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(LibraryContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return evidence.assignment;
    }

    /**
     * *************************************************************************
     *
     * @param assignmentDescriptor Deskriptory řešení
     * @return Kolekce deskriptorů zadaných řešení
     */
    public synchronized ArrayList<SolutionDescriptor> getSolutionDescriptor(
            AssignmentDescriptor assignmentDescriptor) {
        return getSolutionDescriptor(true, assignmentDescriptor);
    }

    /**
     * *************************************************************************
     *
     * @param onlyNotEvaluated Pouze neohodnocená řešení
     * @param assignmentDescriptor Deskriptory řešení
     * @return Kolekce deskriptorů zadaných řešení
     */
    public synchronized ArrayList<SolutionDescriptor> getSolutionDescriptor(
            boolean onlyNotEvaluated,
            AssignmentDescriptor assignmentDescriptor) {
        evidence.solutions.clear();
        evidence.transmiting = true;
        if (onlyNotEvaluated == true) {
            connection.sendMessage("download-homeworks-all-"+ assignmentDescriptor.internalID);
        } else {
            connection.sendMessage("download-homeworks-id-" + assignmentDescriptor.internalID);
        }
        while (evidence.transmiting) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(LibraryContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return evidence.solutions;
    }

    /**
     * *************************************************************************
     *
     * @param internalSolutionID ID hodnoceného řešení
     * @param score Bodové ohodnocení daného řešení
     * @param explanation Vysvětlení daného hodnocení
     * @return
     */
    public boolean sendEvaluation(String internalSolutionID,
            double score, String explanation) {
        evidence.solutionScoreTransmiting = true;
        evidence.solutionScore = false;
        connection.sendMessage("solution-" + internalSolutionID + "-" + score + "-" + explanation);
        int counter = 0;
        while (evidence.solutionScoreTransmiting) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(LibraryContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (counter > 10) {
                evidence.solutionScoreTransmiting = false;
            }
            counter++;
        }
        return evidence.solutionScore;
    }
    
    public boolean closeAllConnections(){
            return connection.close();
    }

}
