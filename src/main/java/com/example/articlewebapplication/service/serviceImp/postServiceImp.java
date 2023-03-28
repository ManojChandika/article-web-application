package com.example.articlewebapplication.service.serviceImp;

import com.example.articlewebapplication.dto.PostDto;
import com.example.articlewebapplication.dto.PostResponse;
import com.example.articlewebapplication.entity.Category;
import com.example.articlewebapplication.entity.Post;
import com.example.articlewebapplication.exception.ResourceNotFoundException;
import com.example.articlewebapplication.repo.CategoryRepo;
import com.example.articlewebapplication.repo.PostRepo;
import com.example.articlewebapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImp implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Create Blog post rest API
    @Override
    public PostDto createPost(PostDto postDto) {
        // Find category
        Category category = categoryRepo.findById(postDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id",postDto.getCategoryId()));

        // Convert Dto to Entity
        Post post = mapToEntity(postDto);
        // Set category
        post.setCategory(category);
        Post newPost = postRepo.save(post);

        // Convert Entity to Dto and return
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    // Get all post rest API
    @Override
    public PostResponse getAllPosts(int pageNo , int pageSize, String sortBy, String sortDir) {

        // Create sort Object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        // Create Pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        // Get content from Page Object

        List<Post> listOfPost = posts.getContent();
        List<PostDto> postDto = listOfPost.stream().map(post ->mapToDto(post) ).collect(Collectors.toList());


        PostResponse postResponse = new PostResponse();
        postResponse.setPostDto(postDto);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    // Get Post by Id
    @Override
    public PostDto getPostById(Long id) {
        Post post=postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
    }

    // Update Post By id
    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        // Find category
        Category category = categoryRepo.findById(postDto
                        .getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id",postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setCategory(category);

        Post postResponse = postRepo.save(post);
        return mapToDto(postResponse);
    }

    // Delete Post
    @Override
    public void deletePost(Long id) {
        Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepo.delete(post);
    }

    // Get all post By Category id
    @Override
    public List<PostDto> getPostsByCategoryId(Long categoryId) {
        categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts = postRepo.findByCategoryId(categoryId);
        return posts.stream().map((post)->mapToDto(post)).collect(Collectors.toList());
    }

    // Convert Dto into Entity
    private Post mapToEntity(PostDto postDto){
        Post post= new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    // Convert Entity into Dto
    private PostDto mapToDto(Post post){
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setContent(post.getContent());
//        postDto.setDescription(post.getDescription());
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
