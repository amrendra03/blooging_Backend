package com.blooging.services.impl;

import com.blooging.entities.Category;
import com.blooging.entities.Post;
import com.blooging.entities.User;
import com.blooging.exceptions.ResourceNotFoundException;
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
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getAllPostByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getAllPostByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keywords) {
        return null;
    }
}
