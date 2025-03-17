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
public class TeamDto {

    private Long id;

    @NotBlank(message = "name is a required field for Team")
    private String name;

    @Length(max = 250, message = "Maximum of 250 characters allowed in Team description.")
    private String description;

}
