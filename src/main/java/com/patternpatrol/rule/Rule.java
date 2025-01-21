package com.patternpatrol.rule;

import com.patternpatrol.model.CheckResult;

public interface Rule<RuleType> {
    CheckResult check(RuleType rule, String targetPath);
}
