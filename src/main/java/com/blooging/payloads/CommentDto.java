package com.blooging.payloads;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {
    private int id;
    private String content;
}
