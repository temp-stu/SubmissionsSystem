package net.hamendi.main;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.hamendi.domain.OutputType;
import net.hamendi.domain.Report;
import net.hamendi.domain.RuleSet;

import org.junit.Test;

public class PMDAdapterImplTest {

	private PMDAdapterImpl adapter;

	@Test
	public void testBuildTest() {
		adapter = new PMDAdapterImpl();
		List<File> files = new ArrayList<File>();
		files.add(new File(""));
		RuleSet rules = new RuleSet("rules.xml");
		Report buildTest = adapter.buildTest(01, files, OutputType.TEXT, rules);
		System.out.println("done>> " + buildTest.toString());
	}

}
