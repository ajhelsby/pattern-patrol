package com.patternpatrol.rule;

public interface Rule<RuleType> {
    void check(RuleType ruleType, String targetPath);
}
