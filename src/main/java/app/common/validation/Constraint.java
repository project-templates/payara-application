package app.common.validation;

public interface Constraint {
    boolean validate(String text);
    String getErrorMessage();
}
