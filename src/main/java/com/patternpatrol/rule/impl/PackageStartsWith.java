package com.patternpatrol.rule.impl;

import com.patternpatrol.helper.TextCheckHelper;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

public class PackageStartsWith implements DirectoryPatternRule {
    @Override
    public CheckResult check(DirectoryRule directoryRule, String targetPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArg(directoryRule.getPatternArg());
        textCheckHelper.setArgs(directoryRule.getPatternArgs());
        textCheckHelper.setIgnore(directoryRule.getIgnorePackages());
        return textCheckHelper.endsWith();
    }
}
