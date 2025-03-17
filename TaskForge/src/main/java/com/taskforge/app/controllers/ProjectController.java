package com.taskforge.app.controllers;

import com.taskforge.app.dto.ProjectDto;
import com.taskforge.app.services.ProjectService;
import com.taskforge.app.utils.LoggerUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController extends LoggerUtil {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects (
            @RequestParam(name = "page", required = false, defaultValue = "0") Byte pageNumber,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy) {
        logger.info("GetAllProjects data requested by the client. Page Number: {}", pageNumber);
        List<ProjectDto> projects = projectService.getAllProjects(pageNumber, sortBy);
        return ResponseEntity.ok(projects);
    }

    @GetMapping(path = "/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long projectId) {
        logger.info("getProjectById data requested by the client for project id {}.", projectId);
        ProjectDto project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody @Valid ProjectDto projectDto) {
    	logger.info("Client requested to save a Project: {}", projectDto.getName());
    	ProjectDto savedProjectDto = projectService.createProject(projectDto);
    	logger.info("Project {} saved. Sending response back to the Client", projectDto.getName());
    	return ResponseEntity.ok(savedProjectDto);
    }
    
    @DeleteMapping(path = "/{projectId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long projectId) {
        logger.info("Client requested to delete the Project with id {}", projectId);
    	return new ResponseEntity<>(projectService.deleteById(projectId), HttpStatus.OK);
    }
}
