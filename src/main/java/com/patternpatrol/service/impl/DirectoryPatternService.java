package com.patternpatrol.service.impl;

import com.patternpatrol.enums.DirectoryPattern;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.rule.impl.PackageContains;
import com.patternpatrol.rule.impl.PackageDomainArchitecture;
import com.patternpatrol.rule.impl.PackageEndsWith;
import com.patternpatrol.rule.impl.PackageImplementation;
import com.patternpatrol.rule.impl.PackageLayeredArchitecture;
import com.patternpatrol.rule.impl.PackageStartsWith;
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
                    PackageLayeredArchitecture packageLayeredArchitecture = new PackageLayeredArchitecture();
                    checks.add(packageLayeredArchitecture.check(rule, file));
                    break;
                case IMPLEMENTATION:
                    PackageImplementation packageImplementation = new PackageImplementation();
                    checks.add(packageImplementation.check(rule, file));
                    break;
                case DOMAIN_DRIVEN:
                    PackageDomainArchitecture packageDomainArchitecture = new PackageDomainArchitecture();
                    checks.add(packageDomainArchitecture.check(rule, file));
                    break;
                case ENDS_WITH:
                    PackageEndsWith packageEndsWith = new PackageEndsWith();
                    checks.add(packageEndsWith.check(rule, file));
                    break;
                case STARTS_WITH:
                    PackageStartsWith packageStartsWith = new PackageStartsWith();
                    checks.add(packageStartsWith.check(rule, file));
                    break;
                case CONTAINS:
                    PackageContains packageContains = new PackageContains();
                    checks.add(packageContains.check(rule, file));
                    break;
                default:
                    System.out.println("Todo");
            }
        }

        return checks;
    }
}
