package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.CartItemDto;
import mta.ltnc.BookStore.entity.CartItem;
import mta.ltnc.BookStore.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartClientService cartService;
    @Autowired
    private BookClientService bookService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorClientService authorService;
    @Autowired
    private PublisherClientService publisherService;
    @Autowired
    private CategoryClientService categoryService;
    @GetMapping("/add-cart")
    public ModelAndView addCartItem(HttpSession session, @Param("itemId") Long itemId){
        if(session.getAttribute("cart") == null){ // Chua co thi khoi tao
            session.setAttribute("cart", new HashMap<Long, CartItemDto>());
        }
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        Integer quantity = 1;
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
            return mav;
        }
        temp.setQuantity(temp.getQuantity() + quantity);
        if( userId != null){ // Neu da dang nhap thi cap nhat lai database
            cartService.updateQuantity(temp.getQuantity(),itemId,userId);
        }
        cart.replace(itemId, temp);
        session.setAttribute("cart", cart);
        return mav;
    }
    @GetMapping("/remove-cart-item")
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
    @GetMapping("/update-cart-item")
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
    @GetMapping("/delete-cart-item")
    public ModelAndView DeleteCartItem(@RequestParam("ItemID")Long itemId ,HttpSession session){
        if (session.getAttribute("cart") == null){
            ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
            mav.addObject("message","Hãy thêm vào giỏ hàng trước!");
            return mav;
        }
        removeCartItem(session,itemId);
        ModelAndView mav = new ModelAndView("client/cart/index::content_cart");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        HashMap<Long,CartItemDto> cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart");
        Long totalPrice = new Long(0);
        Long realPrice = new Long(0);
        for(Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        };
        Long totalPromotion = totalPrice - realPrice;
        mav.addObject("listHot",bookService.getTop4ByOrdOrderByBuysDesc());
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("totalPromotion",totalPromotion);
        mav.addObject("realPrice",realPrice);
        return mav;
    }
    @GetMapping("/index")
    public ModelAndView index(HttpSession session){
        if (session.getAttribute("cart") == null){
            ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
            mav.addObject("message","Hãy thêm vào giỏ hàng trước!");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/cart/index");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        HashMap<Long,CartItemDto> cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart");
        Long totalPrice = new Long(0);
        Long realPrice = new Long(0);
        for(Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        };
        Long totalPromotion = totalPrice - realPrice;
        mav.addObject("listHot",bookService.getTop4ByOrdOrderByBuysDesc());
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("totalPromotion",totalPromotion);
        mav.addObject("realPrice",realPrice);
        return mav;
    }
}
