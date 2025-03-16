package com.taskforge.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskforge.app.annotations.RoleChecker;
import com.taskforge.app.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "name is a required field for User")
    private String name;

    @NotBlank(message = "email is a required field for User")
    @Email(message = "Email not valid")
    private String email;

    @RoleChecker(message = "Invalid priority. Allowed values are [PLATFORM, DEVELOPER, TESTER, LEADERSHIP]")
    private Role role;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime updatedAt;

}
