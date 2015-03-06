package net.hamendi.domain;

import java.util.Set;

public class RuleSet {
	
	private Set<Rule> rules;
	private String path;

	public RuleSet(String path) {
		this.path = path;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	public String getPath() {
		return path;
	}
	
	

}
