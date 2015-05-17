/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.pecinovsky.assev.library.ui;

import cz.pecinovsky.assev.library.SolutionDescriptor;

/**
 *
 * @author Petr
 */
public class Submission {
    
    public String submissionId ;
    
    public Submission(String submissionId){
    this.submissionId = submissionId;
    }
    
    
    public String testSolution(SolutionDescriptor solution){
    return "0.01";
    }
}
