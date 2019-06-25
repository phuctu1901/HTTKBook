package mta.ltnc.BookStore.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Order;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderDTO {
    private Long amount;
    private String name;
}
