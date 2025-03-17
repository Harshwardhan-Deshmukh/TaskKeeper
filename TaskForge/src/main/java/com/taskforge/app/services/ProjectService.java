package com.taskforge.app.services;

import com.taskforge.app.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects(Byte pageNumber, String sortBy);
    ProjectDto getProjectById(Long projectId);
	ProjectDto createProject(ProjectDto projectDto);
	Boolean deleteById(Long projectId);

}
