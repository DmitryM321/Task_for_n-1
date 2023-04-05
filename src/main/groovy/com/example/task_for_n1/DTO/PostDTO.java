package com.example.task_for_n1.DTO;

import com.example.task_for_n1.model.Post;
import com.example.task_for_n1.projection.PostProjectoin;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostDTO {
        private Long id;
        private String title;
        private String body;
        private List<CommentDTO> comments;
        private UserDTO user;
        public static PostDTO fromPostToPostDTO (Post post) {
            PostDTO dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTitle(post.getTitle());
            dto.setBody(post.getBody());
            List<CommentDTO> comments = post.getComments().stream()
                    .map(CommentDTO::fromCommenetToDTO)
                    .collect(Collectors.toList());
            dto.setComments(comments);
            dto.setUser(UserDTO.fromUsertoDTO(post.getUser()));
            return dto;
        }

    }



