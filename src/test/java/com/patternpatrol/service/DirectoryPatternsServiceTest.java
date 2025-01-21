package com.patternpatrol.service;

import com.patternpatrol.enums.DirectoryPattern;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.patternpatrol.service.impl.DirectoryPatternService;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
 

public class DirectoryPatternsServiceTest {

    public static Stream<? extends Arguments> directoryPatternSuccess() {
        return Stream.of(
                Arguments.of("repository", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("service", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("controller", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("model", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("helper", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("util", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("exception", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("enums", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("test", DirectoryPattern.LAYERED, null, null, Collections.unmodifiableSet(new HashSet<>(Arrays.asList("test")))),
                Arguments.of("test", DirectoryPattern.LAYERED, null, null, Collections.unmodifiableSet(new HashSet<>(Arrays.asList("test"))))
        );
    }

    public static Stream<? extends Arguments> directoryPatternFails() {
        return Stream.of(
                Arguments.of("repositories", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("services", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("whatever", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("feature", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("patternpatrol", DirectoryPattern.LAYERED, null, null, null),
                Arguments.of("test", DirectoryPattern.LAYERED, null, null, Collections.unmodifiableSet(new HashSet<>(Arrays.asList("notTest"))))
        );
    }

    @ParameterizedTest
    @MethodSource("directoryPatternSuccess")
    public void testShouldSucceedWithCorrectPackagePatterns(
            String fileName,
            DirectoryPattern directoryPattern,
            String arg,
            Set<String> args,
            Set<String> ignore) {

        // Given
        Set<String> fileNames = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(fileName)));
        DirectoryRule directoryRule = new DirectoryRule();
        directoryRule.setPatternArg(arg);
        directoryRule.setPatternArgs(args);
        directoryRule.setIgnorePackages(ignore);
        DirectoryPatternService directoryPatternService = new DirectoryPatternService();

        // When
        List<CheckResult> checks = directoryPatternService.validate(directoryRule, directoryPattern, fileNames, new ArrayList<>());

        // Then
        assertEquals(1, checks.size());
        assertTrue(checks.get(0).isSuccess());
    }


    @ParameterizedTest
    @MethodSource("directoryPatternFails")
    public void testShouldFailWithIncorrectPackagePatterns(
            String fileName,
            DirectoryPattern directoryPattern,
            String arg,
            Set<String> args,
            Set<String> ignore) {

        // Given
        Set<String> fileNames = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(fileName)));
        DirectoryRule directoryRule = new DirectoryRule();
        directoryRule.setPatternArg(arg);
        directoryRule.setPatternArgs(args);
        directoryRule.setIgnorePackages(ignore);
        DirectoryPatternService directoryPatternService = new DirectoryPatternService();

        // When
        List<CheckResult> checks = directoryPatternService.validate(directoryRule, directoryPattern, fileNames, new ArrayList<>());

        // Then
        assertEquals(1, checks.size());
        assertFalse(checks.get(0).isSuccess());
    }
}
