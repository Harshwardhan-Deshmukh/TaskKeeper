package com.taskforge.app.services.impl;

import com.taskforge.app.dto.ProjectDto;
import com.taskforge.app.dto.TaskDto;
import com.taskforge.app.entities.TaskEntity;
import com.taskforge.app.repositories.TaskRepository;
import com.taskforge.app.services.TaskService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl extends LoggerUtil implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;
    @Override
    public List<TaskDto> listAllTasks(Byte pageNumber) {
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        List<TaskEntity> taskEntities = taskRepository.findAll(pageable).toList();
        logger.info("fetched all the projects for page {} from the database", pageNumber);
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
        // throw new UnsupportedOperationException("Unimplemented method 'listAllTasks'");
    }
}
