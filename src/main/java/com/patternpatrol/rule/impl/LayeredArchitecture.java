package com.patternpatrol.rule.impl;

import com.patternpatrol.enums.DirectoryPatterns;
import com.patternpatrol.rule.DirectoryPatternRule;

import java.util.Set;

public class LayeredArchitecture implements DirectoryPatternRule {
    private static final Set<String> ALLOWED_MODULE_NAMES_PLURAL = Set.of(
            "services", "controllers", "models", "repositories", "helpers", "utils", "exceptions"
    );

    @Override
    public void check(final DirectoryPatterns directoryPatterns, final String targetPath) {
        // Validate input
        if (targetPath == null || targetPath.isEmpty()) {
            throw new IllegalArgumentException("Target path cannot be null or empty.");
        }

        String[] parts = targetPath.split("\\.");
        String moduleName = parts[parts.length - 1].toLowerCase();

        // Check if the module name is allowed
        if (!ALLOWED_MODULE_NAMES_PLURAL.contains(moduleName)) {
            throw new IllegalStateException(
                    String.format("Module '%s' is not allowed in the layered architecture. Allowed modules: %s",
                            moduleName, ALLOWED_MODULE_NAMES_PLURAL)
            );
        }
    }
}
