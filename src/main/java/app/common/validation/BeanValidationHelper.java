package app.common.validation;

import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BeanValidationHelper {
    
    private List<Constraint> constraints = new ArrayList<>();
    
    public BeanValidationHelper pattern(String pattern, String message) {
        Pattern compiledPattern = Pattern.compile(pattern);
        PatternConstraint constraint = new PatternConstraint(compiledPattern, message);
        this.constraints.add(constraint);
        return this;
    }
    
    public BeanValidationHelper maxLength(int maxLength) {
        MaxLengthConstraint constraint = new MaxLengthConstraint(maxLength);
        this.constraints.add(constraint);
        return this;
    }
    
    public boolean validate(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        
        for (Constraint constraint : this.constraints) {
            if (!constraint.validate(value)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(constraint.getErrorMessage())
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
