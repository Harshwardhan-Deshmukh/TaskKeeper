package com.taskforge.app.services.impl;

import com.taskforge.app.dto.TaskDto;
import com.taskforge.app.entities.TaskEntity;
import com.taskforge.app.enums.Priority;
import com.taskforge.app.enums.Status;
import com.taskforge.app.exceptions.ResourceNotFound;
import com.taskforge.app.repositories.TaskRepository;
import com.taskforge.app.services.TaskService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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
        logger.info("fetched all the tasks for page {} from the database", pageNumber);
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
        // throw new UnsupportedOperationException("Unimplemented method 'listAllTasks'");
    }
    @Override
    public TaskDto getTaskById(Long taskId) {
      Optional<TaskEntity> task=taskRepository.findById(taskId);
      logger.info("Querying database to get task data with id {}", taskId);
      return task.map(taskEntity -> {
        logger.info("Task with id {} found, sending the response back to client", taskId);
        return modelMapper.map(taskEntity, TaskDto.class);})
        .orElseThrow(()->{
             logger.info("Task with id {} not found. Throwing an ResourceNotFound Exception", taskId);
            return new ResourceNotFound(String.format("Task with id %d not found", taskId));
        }
        );
          
    }
    @Override
    public TaskDto createTask(TaskDto taskDto) {
        logger.info("Saving Task to the Database");
        TaskEntity inputTask = modelMapper.map(taskDto, TaskEntity.class);
        TaskEntity savedTask = taskRepository.save(inputTask);
        return modelMapper.map(savedTask, TaskDto.class);
    }

    private Boolean taskExistsById(Long taskId) {
        logger.info("Querying the database to check whether Project with id {} exists", taskId);
        return taskRepository.existsById(taskId);
    }

    
    @Override
    public Boolean deleteTaskById(Long taskId) {
        if (taskExistsById(taskId)) {
            logger.info("Task with id {} exists. Deleting the Task", taskId);
            taskRepository.deleteById(taskId);
            return true;
        } else {
            throw new ResourceNotFound("Task not found with id " + taskId);
        }
    }
    @Override
    public TaskDto updateTask(TaskDto taskDto) {
       Long id=taskDto.getId();
       TaskEntity task=taskRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFound("Task with ID " + id + " not found"));
       logger.info("Task with id:{} exists in database. Now updating.. ",id);
       task=taskRepository.save(modelMapper.map(taskDto,TaskEntity.class));
        return modelMapper.map(task,TaskDto.class);
    }
    @Override
    public TaskDto updateTaskStatusById(Long taskId, Status status) {
        TaskEntity task=taskRepository.findById(taskId)
        .orElseThrow(() -> new ResourceNotFound("Task with ID " + taskId + " not found"));
        task.setStatus(status);
        logger.info("Task with id:{} exists in database. Now updating.. ",taskId);
        return modelMapper.map(taskRepository.save(task),TaskDto.class);
    }
    @Override
    public List<TaskDto> listAllTasksByPriority(Priority priority,Byte pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        List<TaskEntity> taskEntities = taskRepository.findByPriority(priority,pageable);
        logger.info("fetched all the tasks for page {} from the database with priority {}", pageNumber,priority);
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
    }
    @Override
    public List<TaskDto> listAllTasksByStatus(Status status,Byte pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        List<TaskEntity> taskEntities = taskRepository.findByStatus(status,pageable);
        logger.info("fetched all the tasks for page {} from the database with status {}", pageNumber,status);
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
    }
}
