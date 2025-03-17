package com.taskforge.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {

    private Long id;

    @NotBlank(message = "name is a required field for Project")
    private String name;

    @Length(max = 250, message = "Maximum 250 characters are allowed in Project description.")
    private String description;

}
