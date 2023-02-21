package com.springboot.blog.utils.mapper;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostMapper implements EntityMapper<PostDTO, Post> {

    @Override
    public Post toEntity(PostDTO dto) {
        Post post = new Post();
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return post;
    }

    @Override
    public PostDTO toDto(Post entity) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(entity.getId());
        postDTO.setTitle(entity.getTitle());
        postDTO.setContent(entity.getContent());
        postDTO.setDescription(entity.getDescription());
        return postDTO;
    }

    @Override
    public List<Post> toEntity(List<PostDTO> listDto) {
        return null;
    }

    @Override
    public List<PostDTO> toDto(List<Post> listEntity) {
        return null;
    }
}
