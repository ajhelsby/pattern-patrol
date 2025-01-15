package com.patternpatrol.model;

import com.patternpatrol.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckResult {
    @Override
    public String toString() {
        return "CheckResult{" +
                "isSuccess=" + isSuccess +
                ", path='" + path + '\'' +
                ", logLevel=" + logLevel +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    private boolean isSuccess;
    private String path;
    private LogLevel logLevel;
    private String errorMessage;
}
