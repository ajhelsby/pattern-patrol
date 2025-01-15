package com.patternpatrol.service;

import com.patternpatrol.exception.PatternPatrolException;
import com.patternpatrol.model.Config;

import java.io.File;

public interface ConfigValidationService {
    Config validateConfig(File configFile) throws PatternPatrolException;
}
