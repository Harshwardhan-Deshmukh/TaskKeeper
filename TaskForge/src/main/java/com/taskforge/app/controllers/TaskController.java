package com.taskforge.app.controllers;

import com.taskforge.app.dto.ProjectDto;
import com.taskforge.app.dto.TaskDto;
import com.taskforge.app.services.TaskService;
import com.taskforge.app.utils.LoggerUtil;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController extends LoggerUtil {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks (
            @RequestParam(name = "page", required = false, defaultValue = "0") Byte pageNumber)
            // @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy)
             {
        logger.info("GetAllTasks data requested by the client. Page Number: {}", pageNumber);
        List<TaskDto> tasks = taskService.listAllTasks(pageNumber);
        return ResponseEntity.ok(tasks);
    }
}
