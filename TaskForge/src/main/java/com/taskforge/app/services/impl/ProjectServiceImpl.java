package com.taskforge.app.services.impl;

import com.taskforge.app.repositories.ProjectRepository;
import com.taskforge.app.services.ProjectService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl extends LoggerUtil implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;
}
