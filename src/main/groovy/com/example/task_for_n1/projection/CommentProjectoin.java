package com.example.task_for_n1.projection;

import com.example.task_for_n1.DTO.CommentDTO;

public interface CommentProjectoin {
    Long getId();
    String getBody();
    Long getPostId();
    default CommentDTO fromCommentProjectionToCommentDTO () {
        CommentDTO dto = new CommentDTO();
        dto.setId(getId());
        dto.setBody(getBody());
        return dto;
    }
}
