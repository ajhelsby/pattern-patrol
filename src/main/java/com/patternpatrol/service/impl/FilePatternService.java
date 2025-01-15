package com.patternpatrol.service.impl;

import com.patternpatrol.enums.FileNamingStandard;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.FileRule;
import com.patternpatrol.rule.impl.EndsWithLayered;
import com.patternpatrol.service.PatternService;

import java.util.List;
import java.util.Set;


public class FilePatternService implements PatternService<FileRule, FileNamingStandard> {

    public List<CheckResult> validate(final FileRule rule, final FileNamingStandard fileNamingStandard, final Set<String> files, final List<CheckResult> existingChecks) {
        List<CheckResult> checks = existingChecks;
        for (String file : files) {
            switch (fileNamingStandard) {
                case ENDS_WITH_LAYERED:
                    EndsWithLayered endsWithLayered = new EndsWithLayered();
                    checks.add(endsWithLayered.check(rule, file));
                    break;
                default:
                    System.out.println("Todo");
            }
        }
        return checks;
    }
}
