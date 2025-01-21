package com.patternpatrol.rule.impl;

import com.patternpatrol.helper.TextCheckHelper;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PackageLayeredArchitecture implements DirectoryPatternRule {
    private static final Set<String> ALLOWED_MODULE_NAMES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    "service", "controller", "model", "repository", "helper", "util", "exception", "enums"
            )));

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArgs(ALLOWED_MODULE_NAMES);
        textCheckHelper.setIgnore(directoryRule.getIgnorePackages());
        textCheckHelper.setLogLevel(directoryRule.getLevel());
        return textCheckHelper.contains();
    }
}
