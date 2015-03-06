package net.hamendi.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import net.hamendi.domain.OutputType;
import net.hamendi.domain.Report;
import net.hamendi.domain.Rule;
import net.hamendi.domain.RuleSet;
import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDAdapterImpl implements PMDAdapter {
	
	public Report buildTest(int submissionId, List<File> files, OutputType format, RuleSet rules) {
		
		PMDConfiguration config = new PMDConfiguration();
		StringBuilder inputPaths = new StringBuilder();
		for (File file : files) {
			inputPaths.append(file.getAbsolutePath() + ",");
		}
		config.setInputPaths(inputPaths.deleteCharAt(inputPaths.lastIndexOf(",")).toString());
		config.setReportFormat(format.toString().toLowerCase());
		config.setRuleSets(rules.getPath());
		File file = new File("temp" + submissionId + ".txt");
		try {
			Files.createFile(file.toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		config.setReportFile(file.getAbsolutePath());
		PMD.doPMD(config);
		return new Report(config.getReportFile());
				
	}

	@Override
	public void setRuleSet(RuleSet ruleset) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void setOutputType() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OutputType getOutputType() {
		// TODO Auto-generated method stub
		return null;
	}

}
