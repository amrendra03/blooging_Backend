package com.blooging.controller;

import com.blooging.config.AppConstants;
import com.blooging.entities.Post;
import com.blooging.payloads.ApiResponse;
import com.blooging.payloads.PostDto;
import com.blooging.payloads.PostResponse;
import com.blooging.services.FileService;
import com.blooging.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy
    ) {
        System.out.println("from get all post ......");
        PostResponse post = this.postService.getAllPost(pageNumber, pageSize, sortBy);
//        System.out.println(pageNumber+" "+pageSize);
        System.out.println(post);

        return new ResponseEntity<PostResponse>(post, HttpStatus.OK);
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

    // Search
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {

        List<PostDto> result = this.postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

    //image upload
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost =  this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    // image download
    @GetMapping(value = "posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        System.out.println("form image download...");
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
