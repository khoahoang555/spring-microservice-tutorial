package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("postId") long postId, @RequestBody CommentDTO commentDTO) {
        System.out.println("this is demo");
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable("postId") long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable("postId") Long postId,
            @PathVariable("id") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO>  updateComment(
            @PathVariable("postId") Long postId,
            @PathVariable("id") Long commentId,
            CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") Long postId,
            @PathVariable("id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }
}
