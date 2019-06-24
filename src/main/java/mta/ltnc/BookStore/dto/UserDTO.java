package mta.ltnc.BookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String role;
    User user;
}
