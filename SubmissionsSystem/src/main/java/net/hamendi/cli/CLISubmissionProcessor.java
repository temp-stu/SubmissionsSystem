package net.hamendi.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.hamendi.core.SubmissionSystem;

public class CLISubmissionProcessor extends SubmissionSystem {

	private String solutionsGlobalDir;


	/**
	 * @see complex.SubmissionSystem#setGroup()
	 */
	public void setGroup() {

	}


	/**
	 * @see complex.SubmissionSystem#submitSolution()
	 */
	public void submitSolution() {

	}


	/**
	 * @see complex.SubmissionSystem#validate()
	 */
	public void validate() {

	}


	/**
	 * @see complex.SubmissionSystem#setRules()
	 */
	public void setRules() {

	}


	/**
	 * @see complex.SubmissionSystem#setSolutionsDirectory()
	 */
	public void setSolutionsDirectory() {

	}


	/**
	 * @see complex.SubmissionSystem#getTestResults()
	 */
	public void getTestResults() {

	}


	/**
	 * @see complex.SubmissionSystem#setDirectory()
	 */
	public void setDirectory() {

	}
	
	/*
	 * pwd:
	 * /Users/gemini/dev/pmd-bin-5.2.3/
	 * run: 
	 * bin/run.sh pmd -d ~/STSProjects/TestingAspectJ/src/main/java/ -f text -R ~/STSProjects/TestingAspectJ/src/main/resources/rules.xml  -version 1.8 -language java
	 * 
	 * or
	 * 
	 * pwd:
	 * /Users/gemini/dev/pmd-bin-5.2.3/
	 * run:
	 * java -Djava.ext.dirs=lib net.sourceforge.pmd.PMD -d ~/STSProjects/TestingAspectJ/src/main/java/ -f text -R ~/STSProjects/TestingAspectJ/src/main/resources/rules.xml
	 *  
	 * -f == file format (text, xml)
	 * -R == ruleset path
	 * default ruleset path: rulesets/java/basic.xml
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
//		System.out.println("Hello");
//		String filename = "";
//		if (0 < args.length) {
//			filename = args[0];
//			File file = new File(filename);
//		}
//		
//		String readLine = "";
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			while ((readLine = br.readLine()) != null) { 
//				System.out.println(readLine);
//			} // end while 
//	    } // end try
//	    catch (IOException e) {
//	    	System.err.println("Error Happened: " + e);
//	    }
		
		CLISubmissionProcessor obj = new CLISubmissionProcessor();
		 
		String command = "/Users/gemini/dev/pmd-bin-5.2.3/bin/run.sh pmd -d src/main/java/ -f text -R src/main/resources/rules.xml  -version 1.8 -language java";
 		String output = obj.executeCommand(command);
 
		System.out.println(output);
	}
	
	private String executeCommand(String command) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}


}
