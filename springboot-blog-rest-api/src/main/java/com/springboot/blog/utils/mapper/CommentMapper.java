package com.springboot.blog.utils.mapper;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentMapper implements EntityMapper<CommentDTO, Comment>{

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        return comment;
    }

    @Override
    public CommentDTO toDto(Comment entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setName(entity.getName());
        commentDTO.setBody(entity.getBody());
        commentDTO.setEmail(entity.getEmail());
        return commentDTO;
    }

    @Override
    public List<Comment> toEntity(List<CommentDTO> listDto) {
        return null;
    }

    @Override
    public List<CommentDTO> toDto(List<Comment> listEntity) {
        return null;
    }
}
