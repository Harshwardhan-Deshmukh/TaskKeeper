package com.taskforge.app.services;

import java.util.List;

import com.taskforge.app.dto.TaskDto;

public interface TaskService {
    List<TaskDto> listAllTasks(Byte pageNumber);
}
