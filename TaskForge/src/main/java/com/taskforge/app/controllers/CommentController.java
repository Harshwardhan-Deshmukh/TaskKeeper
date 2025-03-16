package com.taskforge.app.controllers;

import com.taskforge.app.dto.CommentDto;
import com.taskforge.app.services.CommentService;
import com.taskforge.app.utils.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
public class CommentController extends LoggerUtil {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(@RequestParam(name = "pageNumber",
            required = false, defaultValue = "0") Integer pageNumber) {
        logger.info("Request to get all the comments in page {}", pageNumber);
        List<CommentDto> comments = commentService.getAllComments(pageNumber);
        logger.info("Got all the comments details for page {}. Sending response to client.", pageNumber);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
