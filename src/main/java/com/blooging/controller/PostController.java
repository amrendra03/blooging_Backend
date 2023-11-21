package com.blooging.controller;

import com.blooging.entities.Post;
import com.blooging.payloads.ApiResponse;
import com.blooging.payloads.PostDto;
import com.blooging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    // Create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        System.out.println("from post create.......");
        PostDto post = null;
        try {
            post = this.postService.createPost(postDto, userId, categoryId);
            System.out.println(post);
            return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<PostDto>(post, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> post = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    // get post by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> post = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    // get all  post

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        System.out.println("from get all post ......");
        List<PostDto> post = this.postService.getAllPost(pageNumber, pageSize);
        System.out.println(pageNumber+" "+pageSize);
        System.out.println(post);
        for(PostDto x:post){
            System.out.println(x.getPostId()+" "+x.getContent());
        }
        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }

    // Delete post

    @DeleteMapping("/posts/{postId}")
    public ApiResponse postDelete(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Successfully deleted post", true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
//        System.out.println("form update post controller...");
//        System.out.println(postId);
        PostDto post = this.postService.updatePost(postDto, postId);
//        System.out.println(post);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }
}
