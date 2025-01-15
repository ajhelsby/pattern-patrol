package com.patternpatrol.enums;

import lombok.Getter;

@Getter
public enum LogLevel {
    OFF(0),
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5),
    FATAL(6);

    private final int level;

    LogLevel(final int level) {
        this.level = level;
    }

    public static LogLevel fromName(final String name) {
        try {
            return LogLevel.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid log level: " + name, e);
        }
    }
}
