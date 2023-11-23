package com.blooging.services;

import com.blooging.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
    void  deleteComment(Integer commentId);

}
