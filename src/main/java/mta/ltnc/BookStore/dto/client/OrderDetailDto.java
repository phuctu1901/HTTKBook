package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.OrderDetail;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private OrderDetail orderDetail;
    private Long number;
    private BookDto bookDto;
    public OrderDetailDto(OrderDetail orderDetail){
        this.orderDetail = orderDetail;
        this.number = null;
        this.bookDto = new BookDto(orderDetail.getBook());
    }
}
