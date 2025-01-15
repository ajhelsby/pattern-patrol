package com.patternpatrol.rule.impl;

import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

public class CustomPackageList implements DirectoryPatternRule {
    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath) {
        return null;
    }
}
