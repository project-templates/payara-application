package app.common.validation;

public class MaxLengthConstraint implements Constraint {
    
    private final int maxLength;

    public MaxLengthConstraint(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(String text) {
        return text.length() <= this.maxLength;
    }

    @Override
    public String getErrorMessage() {
        return this.maxLength + "桁以下";
    }
}
