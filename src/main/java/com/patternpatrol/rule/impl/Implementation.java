package com.patternpatrol.rule.impl;

import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

import java.util.Set;

import static com.patternpatrol.constant.ModuleConstants.DIRECTORY_DIVISOR_REGEX;

public class Implementation implements DirectoryPatternRule {
    private static final Set<String> ALLOWED_MODULE_NAMES = Set.of("impl", "implementation");

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath) {
        // Validate input
        if (targetPath == null || targetPath.isEmpty()) {
            return new CheckResult(false, targetPath, directoryRule.getLogLevel(), "Target path cannot be null or empty.");
        }

        String[] parts = targetPath.split(DIRECTORY_DIVISOR_REGEX);
        String moduleName = parts[parts.length - 1].toLowerCase();

        // Check if the module name is allowed
        if (!ALLOWED_MODULE_NAMES.contains(moduleName)) {
            String errorMessage = String.format("Module '%s' is not allowed in the implementation. Allowed modules: %s",
                    moduleName, ALLOWED_MODULE_NAMES);
            return new CheckResult(false, targetPath, directoryRule.getLogLevel(), errorMessage);
        }
        return new CheckResult(true, targetPath, directoryRule.getLogLevel(), "Target path x follows correct directory pattern");
    }
}
