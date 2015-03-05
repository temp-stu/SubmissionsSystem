package net.hamendi.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.sourceforge.pmd.PMD;

public class PMDAdapterImpl implements PMDAdapter {
	
	public void buildSubmission() {
		PMD.main(new String[]{"-d","../",
				"-f","text","-R","/Users/gemini/STSProjects/TestingAspectJ/src/main/resources/rules.xml"});
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec("ls");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
