package com.patternpatrol.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LayeredConstants {
    private static final Set<String> ALLOWED_MODULE_NAMES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    "service",
                    "controller",
                    "model",
                    "repository",
                    "helper",
                    "util",
                    "exception",
                    "enums",
                    "dto",
                    "dao",
                    "middleware",
                    "property",
                    "utility"
            )));

    public static Set<String> getPackageNames() {
        return ALLOWED_MODULE_NAMES;
    }

    public static Set<String> getFileNames() {
        return ALLOWED_MODULE_NAMES.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.toSet());
    }
}
