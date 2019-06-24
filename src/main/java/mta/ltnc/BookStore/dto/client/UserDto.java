package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.entity.UserGroup;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private Boolean status;
    private Timestamp createdDate;
    private UserGroup userGroup;
    public UserDto(User u){
        this.id = u.getId();
        this.userName = u.getUserName();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.name = u.getName();
        this.phone = u.getPhone();
        this.address = u.getAddress();
        this.status = u.getStatus();
        this.createdDate = u.getCreatedDate();
        this.userGroup = u.getUserGroup();
    }
    public User toEntity(){
        User temp = new User();
        temp.setId(id);
        temp.setUserName(userName);
        temp.setEmail(email);
        temp.setPassword(password);
        temp.setName(name);
        temp.setPhone(phone);
        temp.setAddress(address);
        temp.setStatus(status);
        temp.setCreatedDate(createdDate);
        temp.setUserGroup(userGroup);
        return temp;
    }
}
