package com.taskforge.app.services.impl;

import com.taskforge.app.dto.CommentDto;
import com.taskforge.app.entities.CommentEntity;
import com.taskforge.app.repositories.CommentRepository;
import com.taskforge.app.services.CommentService;
import com.taskforge.app.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends LoggerUtil implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 5;

    @Override
    public List<CommentDto> getAllComments(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id"));
        List<CommentEntity> commentEntities = commentRepository.findAll(pageable).toList();
        logger.info("fetched all the comments for page {} from the database", pageNumber);
        return commentEntities
                .stream()
                .map(commentEntity -> modelMapper.map(commentEntity, CommentDto.class))
                .toList();
    }
}
