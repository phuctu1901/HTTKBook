package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.CartItemDto;
import mta.ltnc.BookStore.entity.Order;
import mta.ltnc.BookStore.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/order")
public class OrderClientController {
    @Autowired
    private OrderClientService orderClientService;
    @Autowired
    private CartClientService cartClientService;
    @Autowired
    private AuthorClientService authorService;
    @Autowired
    private PublisherClientService publisherService;
    @Autowired
    private CategoryClientService categoryService;
    @GetMapping("/check-out")
    public ModelAndView checkOut(HttpSession session){
        if (session.getAttribute("cart") == null){
            ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
            mav.addObject("message","Hãy thêm vào giỏ hàng trước!");
            return mav;
        }
        Long userId = (Long)session.getAttribute("userId");
        if(session.getAttribute("order") == null){
            Order temp = orderClientService.createOrder(userId);
            session.setAttribute("order",temp);
        }
        Order order = (Order)session.getAttribute("order");
        ModelAndView mav = new ModelAndView("client/order/check_out");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        HashMap<Long, CartItemDto> cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart");
        Long totalPrice = new Long(0);
        Long realPrice = new Long(0);
        for(Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        };
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("totalPromotion",totalPrice - realPrice);
        mav.addObject("total",realPrice);
        mav.addObject("realTotal",realPrice);
        return mav;
    }
}
