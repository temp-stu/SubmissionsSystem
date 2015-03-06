package net.hamendi.main;

import net.hamendi.domain.OutputType;
import net.hamendi.domain.Rule;
import net.hamendi.domain.RuleSet;

public interface Adapter {
	
	void setRuleSet(RuleSet ruleset);
	void setRule(Rule rule);
	RuleSet getRuleSet();
	void setOutputType();
	OutputType getOutputType();
	

}
