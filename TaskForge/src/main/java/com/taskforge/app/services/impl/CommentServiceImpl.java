package com.taskforge.app.services.impl;

import com.taskforge.app.exceptions.ResourceNotFound;
import com.taskforge.app.repositories.CommentRepository;
import com.taskforge.app.services.CommentService;
import com.taskforge.app.utils.LoggerUtil;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends LoggerUtil implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;

    @Override
    public Boolean deleteById(Long commentId) {
        if (isExists(commentId)) {
            logger.info("Comment with id {} found, deleting the comment", commentId);
            commentRepository.deleteById(commentId);
            return true;
        }
        logger.warn("Comment with id {} not found, throwing an ResourceNotFound Exception", commentId);
        throw new ResourceNotFound(String.format("Comment with id %d not found", commentId));
    }

    private Boolean isExists(Long commentId) {
        logger.info("Querying the database to check whether comment with id {} exists", commentId);
        return commentRepository.existsById(commentId);
    }
}
