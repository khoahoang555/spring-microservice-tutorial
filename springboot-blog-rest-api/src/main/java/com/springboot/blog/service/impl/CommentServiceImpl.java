package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.utils.mapper.CommentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final PostRepository postRepository;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            CommentMapper commentMapper,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment commentSaved = commentRepository.save(comment);

        return commentMapper.toDto(commentSaved);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId)
                .stream().map(comment -> commentMapper.toDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return commentMapper.toDto(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.toDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }
}
