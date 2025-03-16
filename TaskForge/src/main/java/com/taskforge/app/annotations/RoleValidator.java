package com.taskforge.app.annotations;

import com.taskforge.app.enums.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

import static com.taskforge.app.enums.Role.*;

public class RoleValidator implements ConstraintValidator<RoleChecker, Role> {
    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        Set<Role> roles = Set.of(PLATFORM, DEVELOPER, TESTER, LEADERSHIP);
        return role!= null && roles.contains(role);
    }
}
