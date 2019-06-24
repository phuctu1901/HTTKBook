package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Order;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Order order;
    private Long number;
    public OrderDto(Order o){
        this.order = o;
        this.number = null;
    }
}
