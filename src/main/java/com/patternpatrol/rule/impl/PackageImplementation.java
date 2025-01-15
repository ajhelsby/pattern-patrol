package com.patternpatrol.rule.impl;

import com.patternpatrol.helper.TextCheckHelper;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

import java.util.Set;

public class PackageImplementation implements DirectoryPatternRule {
    private static final Set<String> ALLOWED_MODULE_NAMES = Set.of("impl", "implementation");

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArgs(ALLOWED_MODULE_NAMES);
        textCheckHelper.setIgnore(directoryRule.getIgnorePackages());
        return textCheckHelper.contains();
    }
}
