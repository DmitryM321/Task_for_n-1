package com.example.task_for_n1.projection;


import com.example.task_for_n1.DTO.UserPostDTO;

public interface UserProjectoin {
    Long getUserId();
    String getUsername();
    Long getAllPostCount();
    Long getAllCommentsCount();
    Long getLastPostId();
    default UserPostDTO fromUserProjectionToUserDTO() {
        UserPostDTO dto = new UserPostDTO();
        dto.setId(getUserId());
        dto.setUsername(getUsername());
        dto.setPostCount(getAllPostCount());
        dto.setCommentCount(getAllCommentsCount());
        dto.setLastPostId(getLastPostId());
        return dto;
    }
}