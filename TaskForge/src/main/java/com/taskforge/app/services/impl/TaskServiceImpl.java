package com.taskforge.app.services.impl;

import com.taskforge.app.repositories.TaskRepository;
import com.taskforge.app.services.TaskService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl extends LoggerUtil implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;
}
