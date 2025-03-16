package com.taskforge.app.annotations;

import com.taskforge.app.enums.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

import static com.taskforge.app.enums.Status.*;

public class StatusValidator implements ConstraintValidator<StatusChecker, Status> {
    @Override
    public boolean isValid(Status status, ConstraintValidatorContext constraintValidatorContext) {
        Set<Status> statuses = Set.of(TODO, IN_PROGRESS, DONE, BLOCKED);
        return status!= null && statuses.contains(status);
    }
}
