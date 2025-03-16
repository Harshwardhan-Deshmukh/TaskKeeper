package com.taskforge.app.services.impl;

import com.taskforge.app.repositories.TeamRepository;
import com.taskforge.app.services.TeamService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl extends LoggerUtil implements TeamService  {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;
}
