package net.hamendi.core;

import java.util.List;
import java.util.Map;

import net.hamendi.domain.Group;
import net.hamendi.domain.Rule;
import net.hamendi.domain.RuleSet;
import net.hamendi.domain.Solution;

public abstract class SubmissionSystem {

	private InputValidator inputValidator;

	private Map<Group, List<Solution>> groupSolutions;

	public abstract boolean submitSolution(Group group, Solution solution);

	public abstract void validate(Solution sol);

	public abstract void setRule(Rule rule);
	
	public abstract RuleSet getRuleSet();

	public abstract void getTestResults();

	public abstract void setDirectory();
	
	public InputValidator getInputValidator() {
		return inputValidator;
	}

	public void setInputValidator(InputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}

	public Map<Group, List<Solution>> getGroupSolutions() {
		return groupSolutions;
	}

	public void setGroupSolutions(Map<Group, List<Solution>> groupSolutions) {
		this.groupSolutions = groupSolutions;
	}

}
