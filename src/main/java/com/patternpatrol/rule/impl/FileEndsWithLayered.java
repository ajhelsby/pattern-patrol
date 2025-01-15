package com.patternpatrol.rule.impl;

import com.patternpatrol.helper.TextCheckHelper;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.FileRule;
import com.patternpatrol.rule.FileNamingStandardRule;

import java.util.Set;

public class FileEndsWithLayered implements FileNamingStandardRule {
    private static final Set<String> ALLOWED_ENDS_WITH = Set.of(
            "Service", "Controller", "Model", "Repository", "Helper", "Util", "Exception", "Enums"
    );

    @Override
    public CheckResult check(final FileRule fileRule,  final String targetPath) {
        TextCheckHelper textCheckHelper = new TextCheckHelper();
        textCheckHelper.setText(targetPath);
        textCheckHelper.setArgs(ALLOWED_ENDS_WITH);
        textCheckHelper.setIgnore(fileRule.getIgnoreFiles());
        return textCheckHelper.endsWith();
    }
}
