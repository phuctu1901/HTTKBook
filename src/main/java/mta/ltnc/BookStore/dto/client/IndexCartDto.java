package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexCartDto {
    private Integer totalPrice;
    private Integer totalPromotion;
    private Integer totalQuantiry;
    private List<BookDto> listHotBook;
    private List<CartItemDto> cart;
}
