package com.taskforge.app.controllers;

import com.taskforge.app.services.TeamService;
import com.taskforge.app.utils.LoggerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/teams")
public class TeamController extends LoggerUtil {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
}
