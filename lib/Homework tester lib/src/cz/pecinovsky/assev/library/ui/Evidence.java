package cz.pecinovsky.assev.library.ui;

import cz.pecinovsky.assev.library.AssignmentDescriptor;
import cz.pecinovsky.assev.library.SolutionDescriptor;
import java.util.ArrayList;



/**
 * Třída Evidence vytváří evidenci na uchování dat
 * 
* @author Petr Kokeš
 * @version 1.00
 */
public class Evidence {
    
   public ArrayList<Submission> submissions;
   public ArrayList<AssignmentDescriptor> assignment;
   public ArrayList<SolutionDescriptor> solutions;
   public boolean transmiting = true;
   public boolean solutionScoreTransmiting = true;
   public boolean solutionScore = false;
    

/**
 * Konstruktor vytvoří prázdnou evidenci.
 * @throws Exception chyba
 */
    public Evidence()  {        
       submissions = new ArrayList<>();
       solutions = new ArrayList<>();
       assignment = new ArrayList<>();
    }
 
    
    
}