package com.example.task_for_n1.projection;

import com.example.task_for_n1.DTO.PostDTO;

public interface PostProjectoin {
    Long getId();
    String getTitle();
    String getBody();
    default PostDTO fromPostProjectionToPostDTO () {
        PostDTO dto = new PostDTO();
        dto.setId(getId());
        dto.setTitle(getTitle());
        dto.setBody(getBody());
        return dto;
    }
}

