package net.hamendi.core;

import java.util.Collection;

public abstract class SubmissionSystem {

	private InputValidator inputValidator;

	private Collection<Solution> references;

	private Collection<Group> group;

	public abstract void setGroup();

	public abstract void submitSolution();

	public abstract void validate();

	public abstract void setRules();

	public abstract void setSolutionsDirectory();

	public abstract void getTestResults();

	public abstract void setDirectory();

}
