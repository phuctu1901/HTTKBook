package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.CartItemDto;
import mta.ltnc.BookStore.entity.CartItem;
import mta.ltnc.BookStore.service.client.AccountService;
import mta.ltnc.BookStore.service.client.BookService;
import mta.ltnc.BookStore.service.client.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AccountService accountService;
    @PostMapping("/add-cart-item")
    public void addCartItem(HttpSession session, Long itemId, Integer quantity){
        if(session.getAttribute("cart") == null){ // Chua co thi khoi tao
            session.setAttribute("cart", new HashMap<Long, CartItemDto>());
        }
        Long userId = (Long)session.getAttribute("userId");
        HashMap<Long,CartItemDto> cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart"); // Lay gio hang hien tai
        CartItemDto temp = cart.get(itemId);
        if(temp == null){ // Neu khong co thi them vao gio
            CartItemDto cartItemDto = new CartItemDto(bookService.getOneDto(itemId), quantity, session);
            cart.put(itemId, cartItemDto);
            if( userId != null) { // Neu da dang nhap thi cap nhat lai database
                CartItem cartItem = cartItemDto.toCartItem(bookService.findById(itemId), accountService.findUser(userId));
                cartService.save(cartItem);
            }
            session.setAttribute("cart", cart);
            return;
        }
        temp.setQuantity(temp.getQuantity() + quantity);
        if( userId != null){ // Neu da dang nhap thi cap nhat lai database
            cartService.updateQuantity(temp.getQuantity(),itemId,userId);
        }
        cart.replace(itemId, temp);
        session.setAttribute("cart", cart);
    }
    @PostMapping("/remove-cart-item")
    public void removeCartItem(HttpSession session, Long itemId){
        if(session.getAttribute("cart") == null){
            return;
        }
        HashMap<Long,CartItemDto>cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart");
        CartItemDto temp = cart.get(itemId);
        if(temp == null){
            return;
        }
        Long userId = (Long)session.getAttribute("userId");
        temp.setQuantity(temp.getQuantity()-1);
        if(temp.getQuantity() == 0 ){
            cart.remove(cart.get(itemId));
            if(userId != null){
                cartService.removeByItemIdAndUserId(itemId,userId);
            }
        }
        else{
            cart.replace(itemId,temp);
            if(userId != null){
                cartService.updateQuantity(temp.getQuantity(),itemId,userId);
            }
        }
        session.setAttribute("cart", cart);
    }
    @PostMapping("/update-cart-item")
    public void updateCartItem(HttpSession session, @RequestParam("itemId")Long itemId, @RequestParam("quantity")Integer quantity){
        if(session.getAttribute("cart") == null){ // Chua co thi hack a?
            return;
        }
        HashMap<Long,CartItemDto> cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart"); // Lay gio hang hien tai
        CartItemDto temp = cart.get(itemId);
        if(temp == null){ // Neu khong co thi hack a ?
            return;
        }
        Long userId = (Long)session.getAttribute("userId");
        temp.setQuantity(quantity);
        if( userId != null){ // Neu da dang nhap thi cap nhat lai database
            cartService.updateQuantity(quantity,itemId,userId);
        }
        cart.replace(itemId, temp);
        session.setAttribute("cart", cart);
    }
    @GetMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView mav = new ModelAndView("client/cart/index");
        return mav;
    }
}