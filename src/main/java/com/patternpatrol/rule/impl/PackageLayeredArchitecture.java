package com.patternpatrol.rule.impl;

import com.patternpatrol.constant.LayeredConstants;
import com.patternpatrol.helper.TextCheckHelper;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PackageLayeredArchitecture implements DirectoryPatternRule {

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath) {
        // Create list of allowed module names from defaults and args
        Set<String> allowedModuleNames = LayeredConstants.getPackageNames();
        if (directoryRule.getPatternArgs() != null) {
            allowedModuleNames.addAll(directoryRule.getPatternArgs());
        }
        if (directoryRule.getPatternArg() != null) {
            allowedModuleNames.add(directoryRule.getPatternArg());
        }

        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArgs(allowedModuleNames);
        textCheckHelper.setIgnore(directoryRule.getIgnorePackages());
        textCheckHelper.setLogLevel(directoryRule.getLevel());
        return textCheckHelper.contains();
    }
}
