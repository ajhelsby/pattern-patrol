package com.patternpatrol.service.impl;

import com.patternpatrol.enums.FileNamingStandard;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.FileRule;
import com.patternpatrol.rule.impl.FileEndsWith;
import com.patternpatrol.rule.impl.FileEndsWithLayered;
import com.patternpatrol.rule.impl.FileStartsWith;
import com.patternpatrol.service.PatternService;

import java.util.List;
import java.util.Set;


public class FilePatternService implements PatternService<FileRule, FileNamingStandard> {

    public List<CheckResult> validate(final FileRule rule, final FileNamingStandard fileNamingStandard, final Set<String> files, final List<CheckResult> existingChecks) {
        List<CheckResult> checks = existingChecks;
        for (String file : files) {
            switch (fileNamingStandard) {
                case ENDS_WITH:
                    FileEndsWith fileEndsWith = new FileEndsWith();
                    checks.add(fileEndsWith.check(rule, file));
                    break;
                case STARTS_WITH:
                    FileStartsWith fileStartsWith = new FileStartsWith();
                    checks.add(fileStartsWith.check(rule, file));
                    break;
                case ENDS_WITH_LAYERED:
                    FileEndsWithLayered fileEndsWithLayered = new FileEndsWithLayered();
                    checks.add(fileEndsWithLayered.check(rule, file));
                    break;
                default:
                    System.out.println("Todo");
            }
        }
        return checks;
    }
}
