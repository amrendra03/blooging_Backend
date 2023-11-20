package com.blooging.payloads;

import com.blooging.entities.Category;
import com.blooging.entities.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {

    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;
    private UserDto user;
}
