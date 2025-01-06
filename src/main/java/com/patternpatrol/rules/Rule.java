package com.patternpatrol.rules;

public interface Rule<RuleType> {
    void check(RuleType ruleType, String file);
}
