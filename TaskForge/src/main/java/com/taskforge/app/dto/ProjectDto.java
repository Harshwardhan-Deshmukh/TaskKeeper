package com.taskforge.app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {

    private Long id;

    @NotBlank(message = "name is a required field for Project")
    private String name;

    @Max(value = 150, message = "Maximum 150 characters are allowed in Project description.")
    private String description;

}
