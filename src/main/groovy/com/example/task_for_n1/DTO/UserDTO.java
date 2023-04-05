package com.example.task_for_n1.DTO;

import com.example.task_for_n1.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
        private Long id;
        private String username;
        public static UserDTO fromUsertoDTO(Users users) {
                UserDTO dto = new UserDTO();
                dto.setId(users.getId());
                dto.setUsername(users.getUsername());
                return dto;
        }
}


