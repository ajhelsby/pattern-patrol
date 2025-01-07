package com.patternpatrol.model;

import com.patternpatrol.enums.DirectoryPatterns;

public class DirectoryRule extends LogLevelRule {
    private DirectoryPatterns pattern;
    private DirectoryRule directories;
    private FileRule files;
}
