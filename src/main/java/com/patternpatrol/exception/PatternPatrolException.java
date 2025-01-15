package com.patternpatrol.exception;

import org.apache.maven.plugin.MojoExecutionException;

public class PatternPatrolException extends MojoExecutionException {

    public PatternPatrolException(final String message) {
        super(message);
    }
}
