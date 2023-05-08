package com.example.task_for_n1.DTO;

import com.example.task_for_n1.projection.UserProjectoin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPostDTO {
    private Long id;
    private String username;
    private Long postCount;
    private Long commentCount;
    private Long lastPostId;
    }

