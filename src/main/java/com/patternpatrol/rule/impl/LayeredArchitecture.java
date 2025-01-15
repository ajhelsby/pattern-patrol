package com.patternpatrol.rule.impl;

import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.DirectoryPatternRule;

import java.util.Set;

import static com.patternpatrol.constant.ModuleConstants.DIRECTORY_DIVISOR_REGEX;

public class LayeredArchitecture implements DirectoryPatternRule {
    private static final Set<String> ALLOWED_MODULE_NAMES = Set.of(
            "service", "controller", "model", "repository", "helper", "util", "exception", "enums"
    );

    @Override
    public CheckResult check(final DirectoryRule directoryRule, final String targetPath) {
        // Validate input
        if (targetPath == null || targetPath.isEmpty()) {
            return new CheckResult(false, targetPath, directoryRule.getLogLevel(), "Package cannot be null or empty.");
        }

        if (directoryRule.getIgnorePackages().contains(targetPath)) {
            return new CheckResult(true, targetPath, directoryRule.getLogLevel(), "Package " + targetPath + " has been ignored");
        }

        String[] parts = targetPath.split(DIRECTORY_DIVISOR_REGEX);
        String moduleName = parts[parts.length - 1].toLowerCase();

        // Check if the module name is allowed
        if (!ALLOWED_MODULE_NAMES.contains(moduleName)) {
            String errorMessage = String.format("Package '%s' is not allowed in the layered architecture. Allowed modules: %s",
                    moduleName, ALLOWED_MODULE_NAMES);
            return new CheckResult(false, targetPath, directoryRule.getLogLevel(), errorMessage);
        }
        return new CheckResult(true, targetPath, directoryRule.getLogLevel(), "Package " + targetPath + " follows correct directory pattern");
    }
}
