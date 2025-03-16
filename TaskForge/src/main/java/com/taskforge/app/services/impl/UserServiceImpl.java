package com.taskforge.app.services.impl;

import com.taskforge.app.repositories.UserRepository;
import com.taskforge.app.services.UserService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends LoggerUtil implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;
}
