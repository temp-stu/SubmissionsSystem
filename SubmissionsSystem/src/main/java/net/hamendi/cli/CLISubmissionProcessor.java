package net.hamendi.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import net.hamendi.core.InputValidator;
import net.hamendi.domain.Group;
import net.hamendi.domain.Rule;
import net.hamendi.domain.RuleSet;
import net.hamendi.domain.Solution;
import net.hamendi.core.SubmissionSystem;

public class CLISubmissionProcessor extends SubmissionSystem {

	private String solutionsGlobalDir;
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
				
		CLISubmissionProcessor obj = new CLISubmissionProcessor();
		 
		String command = "/Users/gemini/dev/pmd-bin-5.2.3/bin/run.sh pmd -d src/main/java/ -f text -R src/main/resources/rules.xml  -version 1.8 -language java";
 		String output = obj.executeCommand(command);
 
		System.out.println(output);
	}
	
	private String executeCommand(String command) {
		 
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

	
	@Override
	public boolean submitSolution(Group group, Solution solution) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Solution sol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTestResults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirectory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputValidator getInputValidator() {
		// TODO Auto-generated method stub
		return super.getInputValidator();
	}

	@Override
	public void setInputValidator(InputValidator inputValidator) {
		// TODO Auto-generated method stub
		super.setInputValidator(inputValidator);
	}

	@Override
	public Map<Group, List<Solution>> getGroupSolutions() {
		// TODO Auto-generated method stub
		return super.getGroupSolutions();
	}

	@Override
	public void setGroupSolutions(Map<Group, List<Solution>> groupSolutions) {
		// TODO Auto-generated method stub
		super.setGroupSolutions(groupSolutions);
	}

	public String getSolutionsGlobalDir() {
		return solutionsGlobalDir;
	}

	public void setSolutionsGlobalDir(String solutionsGlobalDir) {
		this.solutionsGlobalDir = solutionsGlobalDir;
	}

	@Override
	public void setRule(Rule rule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RuleSet getRuleSet() {
		// TODO Auto-generated method stub
		return null;
	}

}
