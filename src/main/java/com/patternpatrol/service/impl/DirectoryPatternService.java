package com.patternpatrol.service.impl;

import com.patternpatrol.enums.DirectoryPattern;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.impl.Implementation;
import com.patternpatrol.rule.impl.LayeredArchitecture;
import com.patternpatrol.service.PatternService;

import java.util.List;
import java.util.Set;


public class DirectoryPatternService implements PatternService<DirectoryRule, DirectoryPattern> {

    @Override
    public List<CheckResult> validate(final DirectoryRule rule, final DirectoryPattern pattern, final Set<String> files, final List<CheckResult> existingChecks) {
        List<CheckResult> checks = existingChecks;
        for (String file : files) {
            switch (pattern) {
                case LAYERED:
                    LayeredArchitecture layeredArchitecture = new LayeredArchitecture();
                    checks.add(layeredArchitecture.check(rule, file));
                    break;
                case IMPLEMENTATION:
                    Implementation implementation = new Implementation();
                    checks.add(implementation.check(rule, file));
                    break;
                default:
                    System.out.println("Todo");
            }
        }

        return checks;
    }
}
