package com.taskforge.app.services;

import java.util.List;

import com.taskforge.app.dto.TaskDto;
import com.taskforge.app.enums.Priority;
import com.taskforge.app.enums.Status;


public interface TaskService {
    List<TaskDto> listAllTasks(Byte pageNumber);
    TaskDto getTaskById(Long taskId);
	TaskDto createTask(TaskDto taskDto);
	Boolean deleteTaskById(Long taskId);
    TaskDto updateTask(TaskDto taskDto);
    TaskDto updateTaskStatusById(Long taskId,Status status);
    List<TaskDto> listAllTasksByPriority(Priority priority,Byte pageNumber);
    List<TaskDto> listAllTasksByStatus(Status status,Byte pageNumber);
}
