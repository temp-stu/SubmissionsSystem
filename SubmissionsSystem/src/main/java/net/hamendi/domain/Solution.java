package net.hamendi.domain;

import net.hamendi.core.TestResult;
import net.hamendi.core.TestRunner;

public interface Solution {

	public abstract void runTest();

	public abstract SolutionType getSolutionType();

	public abstract Student getStudentDetails();

	public abstract Group getGroupDetails();

	public abstract void save();

	public abstract void setTestResult();

	public abstract TestResult getTestResult();

	public abstract TestRunner getTestRunner();

	public abstract void setTestRunner();

}
