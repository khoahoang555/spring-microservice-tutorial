package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.mapper.PostMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostMapper postMapper,
                           PostRepository postRepository,
                           CategoryRepository categoryRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Category category = categoryRepository.findById(postDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId()));
        Post post = postMapper.toEntity(postDTO);
        post.setCategory(category);
        Post postSaved = postRepository.save(post);

        // convert entity to DTO
        PostDTO postDTOSaved = postMapper.toDto(postSaved);
        postDTOSaved.setCategoryId(postDTO.getCategoryId());
        return postDTOSaved;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<PostDTO> content = posts.getContent().stream().map(post -> postMapper.toDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return postMapper.toDto(post);
    }

    @Override
    public PostDTO updatePost(long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category = categoryRepository.findById(postDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId()));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(post.getDescription());
        post.setCategory(category);

        Post postUpdated = postRepository.save(post);
        PostDTO postDTOSaved = postMapper.toDto(postUpdated);
        postDTOSaved.setCategoryId(postDTO.getCategoryId());
        return postDTOSaved;
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map(post -> postMapper.toDto(post)).collect(Collectors.toList());
    }
}
