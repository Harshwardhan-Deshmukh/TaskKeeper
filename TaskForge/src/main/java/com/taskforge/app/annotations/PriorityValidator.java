package com.taskforge.app.annotations;

import com.taskforge.app.enums.Priority;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import static com.taskforge.app.enums.Priority.*;

public class PriorityValidator implements ConstraintValidator<PriorityChecker, Priority> {
    @Override
    public boolean isValid(Priority priority, ConstraintValidatorContext constraintValidatorContext) {
        Set<Priority> priorities = Set.of(LOW, MEDIUM, HIGH, CRITICAL);
        return priority!= null && priorities.contains(priority);
    }
}
