package com.patternpatrol.helper;

import com.patternpatrol.enums.LogLevel;
import com.patternpatrol.model.CheckResult;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
public class TextCheckHelper {
    private String arg;
    private Set<String> args;
    private String text;
    private LogLevel logLevel;
    private Set<String> ignore;

    public CheckResult startsWith() {
        if (ignore != null && ignore.contains(this.text)) {
            return new CheckResult(true, text, this.logLevel, "Package " + this.text + " has been ignored");
        }

        if (this.arg == null && this.args == null) {
            return new CheckResult(false, this.text, this.logLevel, "No naming args provided");
        }

        if (this.text.startsWith(this.arg)) {
            return new CheckResult(true, this.text, this.logLevel, "Valid prefix: " + this.arg);
        }

        for (String suffix : this.args) {
            if (text.startsWith(suffix)) {
                return new CheckResult(true, this.text, this.logLevel, "Valid prefix: " + suffix);
            }
        }

        return new CheckResult(false, this.text, this.logLevel, "Invalid prefix for path: " + this.text);
    }

    public CheckResult endsWith() {
        if (ignore != null && ignore.contains(this.text)) {
            return new CheckResult(true, text, this.logLevel, "Package " + this.text + " has been ignored");
        }

        if (this.arg == null && this.args == null) {
            return new CheckResult(false, this.text, this.logLevel, "No naming args provided");
        }

        if (this.arg != null && this.text.endsWith(this.arg)) {
            return new CheckResult(true, this.text, this.logLevel, "Valid suffix: " + this.arg);
        }

        for (String suffix : this.args) {
            if (text.endsWith(suffix)) {
                return new CheckResult(true, this.text, this.logLevel, "Valid suffix: " + suffix);
            }
        }

        return new CheckResult(false, this.text, this.logLevel, "Invalid suffix for path: " + this.text);
    }

    public CheckResult contains() {
        // Validate input
        if (this.text == null || this.text.isEmpty()) {
            return new CheckResult(false, this.text, this.logLevel, "Package cannot be null or empty.");
        }

        if (ignore != null && ignore.contains(this.text)) {
            return new CheckResult(true, text, this.logLevel, "Package " + this.text + " has been ignored");
        }

        // Check if the module name is allowed
        if (!this.args.contains(this.text)) {
            String errorMessage = String.format("Package '%s' is not allowed in the layered architecture. Allowed modules: %s",
                    this.text, this.args);
            return new CheckResult(false, this.text, this.logLevel, errorMessage);
        }
        return new CheckResult(true, this.text, this.logLevel, "Package " + this.text + " follows correct directory pattern");
    }
}
