package com.patternpatrol.rule.impl;

import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.FileRule;
import com.patternpatrol.rule.FileNamingStandardRule;

import java.util.Set;

public class EndsWithLayered implements FileNamingStandardRule {
    private static final Set<String> ALLOWED_ENDS_WITH = Set.of(
            "Service", "Controller", "Model", "Repository", "Helper", "Util", "Exception", "Enums"
    );

    @Override
    public CheckResult check(FileRule fileRule, String targetPath) {
        // Check if targetPath ends with any of the allowed suffixes
        for (String suffix : ALLOWED_ENDS_WITH) {
            if (targetPath.endsWith(suffix)) {
                // If it matches, return a successful CheckResult (adjust based on your needs)
                return new CheckResult(true, targetPath, fileRule.getLevel(), "Valid ending: " + suffix);
            }
        }

        // If none of the suffixes match, return a failed CheckResult
        return new CheckResult(false, targetPath, fileRule.getLevel(), "Invalid ending for path: " + targetPath);
    }
}
