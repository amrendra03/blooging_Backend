package com.blooging.services;

import com.blooging.entities.Post;
import com.blooging.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    Post updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<Post> getAllPostByCategory(Integer categoryId);

    List<Post> getAllPostByUser(Integer userId);

    List<Post> searchPosts(String keywords);
}
