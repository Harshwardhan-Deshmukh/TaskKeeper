package com.taskforge.app.services.impl;

import com.taskforge.app.dto.ProjectDto;
import com.taskforge.app.entities.ProjectEntity;
import com.taskforge.app.exceptions.ResourceNotFound;
import com.taskforge.app.repositories.ProjectRepository;
import com.taskforge.app.services.ProjectService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl extends LoggerUtil implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;

    @Override
    public List<ProjectDto> getAllProjects(Byte pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.ASC, sortBy));
        List<ProjectEntity> projectEntities = projectRepository.findAll(pageable).toList();
        logger.info("fetched all the projects for page {} from the database sorted by {}", pageNumber, sortBy);
        return projectEntities.stream().map(projectEntity -> modelMapper.map(projectEntity, ProjectDto.class)).toList();
    }

    @Override
    public ProjectDto getProjectById(Long projectId) {
        Optional<ProjectEntity> projectEntityOptional = projectRepository.findById(projectId);
        logger.info("Querying database to get project data with id {}", projectId);
        return projectEntityOptional.map(projectEntity -> {
            logger.info("Project with id {} found, sending the response back to client", projectId);
            return modelMapper.map(projectEntity, ProjectDto.class);
        }).orElseThrow(() -> {
            logger.info("Project with id {} not found. Throwing an ResourceNotFound Exception", projectId);
            return new ResourceNotFound(String.format("Project with id %d not found", projectId));
        });
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        logger.info("Saving Project to the Database");
        ProjectEntity inputProject = modelMapper.map(projectDto, ProjectEntity.class);
        ProjectEntity savedProject = projectRepository.save(inputProject);
        return modelMapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public Boolean deleteById(Long projectId) {
        if (projectExistsById(projectId)) {
            logger.info("Project with id {} exists. Deleting the Project", projectId);
            projectRepository.deleteById(projectId);
            return true;
        } else {
            throw new ResourceNotFound("Project not found with id " + projectId);
        }
    }

    private Boolean projectExistsById(Long projectId) {
        logger.info("Querying the database to check whether Project with id {} exists", projectId);
        return projectRepository.existsById(projectId);
    }
}
