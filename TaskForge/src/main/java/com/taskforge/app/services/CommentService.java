package com.taskforge.app.services;

import com.taskforge.app.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments(Integer pageNumber);
}
