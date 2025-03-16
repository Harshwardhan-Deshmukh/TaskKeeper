package com.taskforge.app.controllers;

import com.taskforge.app.dto.ProjectDto;
import com.taskforge.app.services.ProjectService;
import com.taskforge.app.utils.LoggerUtil;
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
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Byte pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {
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
}
