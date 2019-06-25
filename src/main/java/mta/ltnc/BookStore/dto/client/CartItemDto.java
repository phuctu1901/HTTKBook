package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Book;
import mta.ltnc.BookStore.entity.CartItem;
import mta.ltnc.BookStore.entity.User;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Timestamp dateAdded;
    private Long userId;
    private Long itemId;
    private String name;
    private String author;
    private String image;
    private Integer price;
    private Integer promotion_price;
    private Integer inventory;
    private Boolean selected;
    public CartItemDto(BookDto book, Integer quantity, HttpSession session){
        this.quantity = quantity;
        this.dateAdded = new Timestamp(new Date().getTime());
        this.userId = session.getAttribute("userId") == null ? null : (Long)session.getAttribute("userId");
        this.itemId = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.image = book.getImage1();
        this.price = book.getPrice();
        this.promotion_price = book.getPromotionPrice();
        this.inventory = book.getInventory();
        this.selected = true;
    }
    public CartItemDto(CartItem cartItem){
        this.id = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.dateAdded = cartItem.getDateAdded();
        this.userId = cartItem.getUser().getId();
        this.itemId = cartItem.getBook().getId();
        this.name = cartItem.getBook().getName();
        this.author = cartItem.getBook().getAuthor().getName();
        this.image = cartItem.getBook().getImage1();
        this.price = cartItem.getBook().getPrice();
        this.promotion_price = cartItem.getBook().getPromotionPrice();
        this.inventory = cartItem.getBook().getInventory();
        this.selected = true;
    }
    public CartItem toCartItem(Book book, User user){
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setDateAdded(dateAdded);
        cartItem.setId(id);
        cartItem.setQuantity(quantity);
        cartItem.setUser(user);
        return cartItem;
    }
}
