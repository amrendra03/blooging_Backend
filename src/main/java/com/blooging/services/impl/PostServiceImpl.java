package com.blooging.services.impl;

import com.blooging.entities.Category;
import com.blooging.entities.Post;
import com.blooging.entities.User;
import com.blooging.exceptions.ResourceNotFoundException;
import com.blooging.payloads.CategoryDto;
import com.blooging.payloads.PostDto;
import com.blooging.repository.CategoryRepo;
import com.blooging.repository.PostRepo;
import com.blooging.repository.UserRepo;
import com.blooging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        System.out.println("Post service......");

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id",userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.jpg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost  = this.postRepo.save(post);

        System.out.println("from post service.........ok");

        return  this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> allPost = this.postRepo.findAll();
        List<PostDto> postDto = allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat  = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
        List<Post> post = this.postRepo.findByCategory(cat);
        List<PostDto> postDto = post.stream().map((posts)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","User id",userId));
        List<Post> post = this.postRepo.findByUser(user);
        List<PostDto> postDto = post.stream().map((posts)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<Post> searchPosts(String keywords) {
        return null;
    }
}
