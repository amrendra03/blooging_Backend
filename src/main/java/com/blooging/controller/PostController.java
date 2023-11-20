package com.blooging.controller;

import com.blooging.entities.Post;
import com.blooging.payloads.PostDto;
import com.blooging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
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
            return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<PostDto>(post, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
