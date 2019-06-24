package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    public String email;
    public String password;
    public Boolean rememberMe;
}
