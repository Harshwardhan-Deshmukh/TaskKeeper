package com.taskforge.app.controllers;

import com.taskforge.app.services.UserService;
import com.taskforge.app.utils.LoggerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController extends LoggerUtil {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
