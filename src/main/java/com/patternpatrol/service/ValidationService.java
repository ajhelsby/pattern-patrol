package com.patternpatrol.service;

import com.patternpatrol.enums.LogLevel;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.Config;
import com.patternpatrol.model.DirectoryRule;
import com.patternpatrol.model.FileRule;
import com.patternpatrol.service.impl.DirectoryPatternService;
import com.patternpatrol.service.impl.FilePatternService;
import com.patternpatrol.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class ValidationService {

    private final DirectoryPatternService directoryPatternService = new DirectoryPatternService();
    private final FilePatternService filePatternService = new FilePatternService();

    public List<CheckResult> validate(final Config config, final LogLevel failOn) throws IOException {
        List<CheckResult> checks = new ArrayList<>();
        List<String> reducedPaths = FileUtils.getAllPackagesAtBase(config);
        // Validate directories
        if (config.getDirectoriesRule() != null) {
            validateFilesAndPackages(config.getFileRule(), config.getDirectoriesRule(), reducedPaths, checks);
        }
        System.out.println(failOn);
        checks.stream().forEach(line -> System.out.println(line));
        return checks;
    }

    private List<CheckResult> validateFilesAndPackages(final FileRule fileRule, final DirectoryRule directoryRule, final List<String> files, List<CheckResult> checks) {

        if (fileRule != null) {
            Set<String> fileNames = getFileNames(files);
            checks = filePatternService.validate(fileRule, fileRule.getNaming(), fileNames, checks);
        }
        // Validate directories
        if (directoryRule != null) {
            Set<String> packageNames = getPackageNames(files);
            checks = directoryPatternService.validate(directoryRule, directoryRule.getPattern(), packageNames, checks);
        }

        if (directoryRule.getDirectoriesRule() != null) {
            // Recursively call validate files and packages to handle nested directory config
            List<String> nextLevelFiles = getNextPackageLevel(files);
            return validateFilesAndPackages(directoryRule.getFileRule(), directoryRule.getDirectoriesRule(), nextLevelFiles, checks);
        }

        return checks;
    }

    private List<String> getNextPackageLevel(final List<String> files) {
        return files.stream()
                .map(file -> {
                    String[] parts = file.split("/");
                    return Arrays.stream(parts)
                            .skip(1) // Skip the first element
                            .collect(Collectors.joining("/")); // Join the rest back with "/"
                })
                .collect(Collectors.toList());
    }

    private Set<String> getPackageNames(final List<String> reducedPaths) {
        return reducedPaths.stream()
                .map(path -> {
                    String[] parts = path.split("/");
                    String b = parts.length > 1 ? parts[0] : null;
                    return b;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }


    private Set<String> getFileNames(final List<String> reducedPaths) {
        return reducedPaths.stream()
                .map(path -> {
                    String[] parts = path.split("/");
                    String b = parts.length == 1 ? parts[0] : "";
                    String c = b.replace(".java", "");
                    return c;
                })
                .filter(Objects::nonNull)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
    }

}
