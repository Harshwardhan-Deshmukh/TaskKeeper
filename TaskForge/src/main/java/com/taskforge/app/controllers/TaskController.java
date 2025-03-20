package com.taskforge.app.controllers;
import com.taskforge.app.dto.TaskDto;
import com.taskforge.app.enums.Priority;
import com.taskforge.app.enums.Status;
import com.taskforge.app.services.TaskService;
import com.taskforge.app.utils.LoggerUtil;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController extends LoggerUtil {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks (
            @RequestParam(name = "page", required = false, defaultValue = "0") Byte pageNumber
            )
            // @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy)
             {
        logger.info("GetAllTasks data requested by the client. Page Number: {}", pageNumber);
        List<TaskDto> tasks = taskService.listAllTasks(pageNumber);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<TaskDto>> getAllTasksByStatus (
            @RequestParam(name = "page", required = false, defaultValue = "0") Byte pageNumber,
            @RequestParam(name = "status", required = false, defaultValue = "IN_PROGRESS") Status status
            )
            // @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy)
             {
        logger.info("GetAllTasksByStatus data requested by the client. Page Number: {},Status {}", pageNumber,status);
        List<TaskDto> tasks = taskService.listAllTasksByStatus(status,pageNumber);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/priority")
    public ResponseEntity<List<TaskDto>> getAllTasksByPriority (
            @RequestParam(name = "page", required = false, defaultValue = "0") Byte pageNumber,
            @RequestParam(name = "priority", required = false, defaultValue = "HIGH") Priority priority
            )
            // @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy)
             {
        logger.info("GetAllTasksByPriority data requested by the client. Page Number: {},Priority {}", pageNumber,priority);
        List<TaskDto> tasks = taskService.listAllTasksByPriority(priority,pageNumber);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        logger.info("getTaskById data requested by the client for task id {}.", taskId);
        TaskDto task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto) {
    	logger.info("Client requested to save a Task: {}", taskDto.getTitle());
    	TaskDto savedTaskDto = taskService.createTask(taskDto);
    	logger.info("Task {} saved. Sending response back to the Client", taskDto.getTitle());
    	return ResponseEntity.ok(savedTaskDto);
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody @Valid TaskDto taskDto) {
    	logger.info("Client requested to update a Task: {}", taskDto.getTitle());
    	TaskDto savedTaskDto = taskService.updateTask(taskDto);
    	logger.info("Task {} saved. Sending response back to the Client", taskDto.getTitle());
    	return ResponseEntity.ok(savedTaskDto);
    }

    @PutMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long taskId,
        @RequestParam(name = "status", required = false, defaultValue = "TODO") Status status
    ) {
    	logger.info("Client requested to update a TaskID: {}", taskId);
    	TaskDto savedTaskDto = taskService.updateTaskStatusById(taskId,status);
    	logger.info("Task {} saved. Sending response back to the Client", savedTaskDto.getStatus());
    	return ResponseEntity.ok(savedTaskDto);
    }
    
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long taskId) {
        logger.info("Client requested to delete the Task with id {}", taskId);
    	return new ResponseEntity<>(taskService.deleteTaskById(taskId), HttpStatus.OK);
    }
    
}
