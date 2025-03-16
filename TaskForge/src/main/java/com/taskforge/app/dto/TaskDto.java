package com.taskforge.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskforge.app.annotations.PriorityChecker;
import com.taskforge.app.annotations.StatusChecker;
import com.taskforge.app.enums.Priority;
import com.taskforge.app.enums.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

    private Long id;

    @NotBlank(message = "title is a required field for Task")
    private String title;

    @Max(value = 150, message = "Maximum 150 characters are allowed in Task description.")
    private String description;

    @StatusChecker(message = "Invalid status. Allowed values are [TODO, IN_PROGRESS, DONE, BLOCKED]")
    private Status status;

    @PriorityChecker(message = "Invalid priority. Allowed values are [LOW, MEDIUM, HIGH, CRITICAL]")
    private Priority priority;

    @Future(message = "Task should have due date in future")
    private LocalDate dueDate;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "hh:mm:ss dd:MM:yyyy")
    private LocalDateTime updatedAt;

}
