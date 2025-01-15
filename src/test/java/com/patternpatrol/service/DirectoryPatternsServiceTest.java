package com.patternpatrol.service;

import com.patternpatrol.enums.DirectoryPattern;
import com.patternpatrol.model.CheckResult;
import com.patternpatrol.model.DirectoryRule;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.patternpatrol.service.impl.DirectoryPatternService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectoryPatternsServiceTest {

    public static Stream<? extends Arguments> layeredArchitectureSuccess() {
        return Stream.of(
                Arguments.of("com/patternpatrol/repository"),
                Arguments.of("com/patternpatrol/random/service"),
                Arguments.of("com/patternpatrol/any/thing/goes/controller"),
                Arguments.of("com/patternpatrol/model"),
                Arguments.of("com/patternpatrol/helper"),
                Arguments.of("util"),
                Arguments.of("exception"),
                Arguments.of(("enums"))
        );
    }

    public static Stream<? extends Arguments> layeredArchitectureFails() {
        return Stream.of(
                Arguments.of("com/patternpatrol/repositories"),
                Arguments.of("com/patternpatrol/random/services"),
                Arguments.of("com/patternpatrol/any/thing/goes/whatever"),
                Arguments.of("com/patternpatrol/feature"),
                Arguments.of("com/patternpatrol")
        );
    }

    @ParameterizedTest
    @MethodSource("layeredArchitectureSuccess")
    public void testShouldSucceedWithCorrectlyLayeredArchitecture(String fileName) {
        // Given
        Set<String> fileNames = Set.of(fileName);
        DirectoryRule directoryRule = new DirectoryRule();
        DirectoryPatternService directoryPatternService = new DirectoryPatternService();

        // When
        List<CheckResult> checks = directoryPatternService.validate(directoryRule, DirectoryPattern.LAYERED, fileNames, List.of());

        // Then
        assertEquals(1, checks.size());
        assertTrue(checks.get(0).isSuccess());
    }


    @ParameterizedTest
    @MethodSource("layeredArchitectureFails")
    public void testShouldFailWithIncorrectlyLayeredArchitecture(String fileName) {
        // Given
        Set<String> fileNames = Set.of(fileName);
        DirectoryRule directoryRule = new DirectoryRule();
        DirectoryPatternService directoryPatternService = new DirectoryPatternService();

        // When
        List<CheckResult> checks = directoryPatternService.validate(directoryRule, DirectoryPattern.LAYERED, fileNames, List.of());

        // Then
        assertEquals(1, checks.size());
        assertFalse(checks.get(0).isSuccess());
    }
}
