package net.hamendi.core;

import java.io.BufferedInputStream;
import java.io.Serializable;

public class StudentSolution implements Solution, Serializable {

	private SolutionType solutionType;

	private TestResult result;

	private BufferedInputStream solution;

	private Student student;


	/**
	 * @see complex.Solution#runTest()
	 */
	public void runTest() {

	}


	/**
	 * @see complex.Solution#getSolutionType()
	 */
	public SolutionType getSolutionType() {
		return null;
	}


	/**
	 * @see complex.Solution#getStudentDetails()
	 */
	public Student getStudentDetails() {
		return null;
	}


	/**
	 * @see complex.Solution#getGroupDetails()
	 */
	public Group getGroupDetails() {
		return null;
	}


	/**
	 * @see complex.Solution#save()
	 */
	public void save() {

	}


	/**
	 * @see complex.Solution#setTestResult()
	 */
	public void setTestResult() {

	}


	/**
	 * @see complex.Solution#getTestResult()
	 */
	public TestResult getTestResult() {
		return null;
	}


	/**
	 * @see complex.Solution#getTestRunner()
	 */
	public TestRunner getTestRunner() {
		return null;
	}


	/**
	 * @see complex.Solution#setTestRunner()
	 */
	public void setTestRunner() {

	}

}
