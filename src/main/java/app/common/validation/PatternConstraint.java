package app.common.validation;

import java.util.regex.Pattern;

public class PatternConstraint implements Constraint {
    
    private final Pattern pattern;
    private final String message;

    public PatternConstraint(Pattern pattern, String message) {
        this.pattern = pattern;
        this.message = message;
    }

    @Override
    public boolean validate(String text) {
        return this.pattern.matcher(text).matches();
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}
