package com.patternpatrol.service;

import com.patternpatrol.enums.DirectoryPatterns;

import java.util.List;

public class DirectoryPatternsService {
    void execute(final DirectoryPatterns directoryPattern, final List<String> files) {
        for (String file: files) {
            switch (directoryPattern) {
                case LAYERED_ARCHITECTURE:
                    System.out.println("Todo");
                default:
                    System.out.println("Todo");
            }
        }
    }
}
