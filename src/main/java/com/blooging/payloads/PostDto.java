package com.blooging.payloads;

import com.blooging.entities.Category;
import com.blooging.entities.Comment;
import com.blooging.entities.User;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
