package com.patternpatrol.model;

import com.patternpatrol.enums.FileNamingStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileRule extends LogLevelRule implements RuleType {
    private FileNamingStandard naming;
}
