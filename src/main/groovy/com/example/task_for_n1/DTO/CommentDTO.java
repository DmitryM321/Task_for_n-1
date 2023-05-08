package com.example.task_for_n1.DTO;

import com.example.task_for_n1.model.Comment;
import com.example.task_for_n1.projection.CommentProjectoin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String body;
    public static CommentDTO fromCommenetToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }
}


