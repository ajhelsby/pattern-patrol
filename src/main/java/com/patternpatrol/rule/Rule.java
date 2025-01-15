package com.patternpatrol.rule;

import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.model.RuleType;

public interface Rule<RuleType> {
    CheckResult check(RuleType rule, String targetPath);
}
