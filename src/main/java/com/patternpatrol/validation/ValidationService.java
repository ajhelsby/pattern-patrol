package com.patternpatrol.validation;

import com.patternpatrol.enums.LogLevel;
import com.patternpatrol.model.Config;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {

    public List<String> validate(final Config config, final LogLevel failOn) {
        List<CheckResult> checks = new ArrayList<>();
        return checks;
    }
}
