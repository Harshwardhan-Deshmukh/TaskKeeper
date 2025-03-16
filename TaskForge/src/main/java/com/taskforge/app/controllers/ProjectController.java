package com.taskforge.app.controllers;

import com.taskforge.app.services.ProjectService;
import com.taskforge.app.utils.LoggerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController extends LoggerUtil {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
}
