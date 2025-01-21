package com.patternpatrol.rule.impl;

import com.patternpatrol.helper.TextCheckHelper;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.FileRule;
import com.patternpatrol.rule.FileNamingStandardRule;

public class FileEndsWith implements FileNamingStandardRule {
    @Override
    public CheckResult check(final FileRule fileRule, final String targetPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArg(fileRule.getNamingArg());
        textCheckHelper.setArgs(fileRule.getNamingArgs());
        textCheckHelper.setIgnore(fileRule.getIgnoreFiles());
        textCheckHelper.setLogLevel(fileRule.getLevel());
        return textCheckHelper.endsWith();

    }
}
