package com.taskforge.app.controllers;

import com.taskforge.app.services.CommentService;
import com.taskforge.app.utils.LoggerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comments")
public class CommentController extends LoggerUtil {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long commentId) {
        Boolean isDeleted = commentService.deleteById(commentId);
        return ResponseEntity.ok(isDeleted);
    }
}
