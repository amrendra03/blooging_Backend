package com.blooging.services.impl;

import com.blooging.entities.Comment;
import com.blooging.entities.Post;
import com.blooging.exceptions.ResourceNotFoundException;
import com.blooging.payloads.CommentDto;
import com.blooging.payloads.PostDto;
import com.blooging.repository.CommentRepo;
import com.blooging.repository.PostRepo;
import com.blooging.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post Id",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Commnet","Comment Id",commentId));
        this.commentRepo.delete(com);
    }
}
