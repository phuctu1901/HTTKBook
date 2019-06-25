package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.AccountDto;
import mta.ltnc.BookStore.dto.client.CartItemDto;
import mta.ltnc.BookStore.dto.client.OrderDetailDto;
import mta.ltnc.BookStore.dto.client.OrderDto;
import mta.ltnc.BookStore.entity.CartItem;
import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private BookClientService bookService;
    @Autowired
    private AuthorClientService authorService;
    @Autowired
    private PublisherClientService publisherService;
    @Autowired
    private CategoryClientService categoryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderClientService orderService;
    @Autowired
    private CartClientService cartService;
    @GetMapping("/register")
    public ModelAndView register(HttpSession session){
        ModelAndView mav = new ModelAndView("client/account/register");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        return mav;
    }
    @PostMapping("/register")
    public ModelAndView registerPost(@Valid @ModelAttribute("user")User user,
                                     BindingResult result,
                                     HttpSession session){
        if (result.hasErrors()) {
            return new ModelAndView("client/shared/error");
        }
        String message = "";
        switch (accountService.checkSameAcc(user.getUserName(),user.getEmail())){
            case 0:
                message = "Tạo tài khoản thành công!";
                user.setCreatedDate(new Timestamp(new Date().getTime()));
                user.setStatus(true);
                accountService.saveUser(user);
                break;
            case 1:
                message = "Email đã sử dụng!";
                break;
            case 2:
                message = "Tên tài khoản đã sử dụng!";
                break;
            default:
                message = "Email và tên tài khoản đã sử dụng!";
                break;
        }
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        mav.addObject("message",message);
        return mav;
    }
    @PostMapping("/login")
    public  ModelAndView login(@Valid @ModelAttribute("account") AccountDto account,
                               BindingResult result, HttpSession session){
        if (result.hasErrors()) {
            return new ModelAndView("client/shared/error");
        }
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        boolean check = accountService.checkLogin(account.email,account.password);
        if(!check){
            mav.addObject("message","Đăng nhập thất bại!");
            mav.addObject("title","Điều hướng");
            return mav;
        }
        User u = accountService.getUser(account.email,account.password);
        session.setAttribute("userId",u.getId());
        session.setAttribute("userName",u.getUserName());
        HashMap<Long, CartItemDto> currentCart = (HashMap<Long, CartItemDto>)session.getAttribute("cart");
        HashMap<Long, CartItemDto> dbCart = accountService.getCart(u.getId());
        if (currentCart != null){
            for (Long key : dbCart.keySet()) {
                CartItemDto temp = currentCart.get(key);
                if(temp == null){ // Neu trong datsbase khong co thi them vao
                    dbCart.put(key,currentCart.get(key));
                    CartItem cartItem = temp.toCartItem(bookService.findById(temp.getId()),u);
                    cartService.save(cartItem);
                }
                else{ // Neu co thi cong them so luong tuong ung
                    temp.setQuantity(temp.getQuantity()+currentCart.get(key).getQuantity());
                    dbCart.replace(key,temp);
                    cartService.updateQuantity(temp.getQuantity(),key,u.getId());
                }
            }
        }
        session.setAttribute("cart", dbCart);
        mav.addObject("message","Đăng nhập thành công!");
        mav.addObject("title","Điều hướng");
        HelpModelAndView.dataForLayout( mav,categoryService,publisherService,authorService,session);
        return mav;
    }
    @GetMapping("/list-order")
    public ModelAndView listOrder(HttpSession session){
        if(session.getAttribute("userId") == null){
            ModelAndView mav = new ModelAndView("client/home/index");
            HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
            mav.addObject("Trang chủ");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/account/list_order");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        List<OrderDto> order = orderService.findAllByUserId((long)session.getAttribute("userId"));
        mav.addObject("order",order);
        return mav;
    }
    @GetMapping("/order-detail")
    public ModelAndView detailOrder(@RequestParam("orderId")Long orderId,@RequestParam("number")Long number,HttpSession session){
        List<OrderDetailDto> orderDetails = orderService.findAllOrderDetailByOrderId(orderId);
        ModelAndView mav = new ModelAndView("client/account/order_detail");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        mav.addObject("model",orderDetails);
        mav.addObject("number",number);
        return mav;
    }
    @GetMapping("/user-profile")
    public ModelAndView userProfile(HttpSession session){
        if(session.getAttribute("userId") == null){
            ModelAndView mav = new ModelAndView("client/home/index");
            HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
            mav.addObject("Trang chủ");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/account/user_profile");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        long userId = (long)session.getAttribute("userId");
        User user = accountService.findUser(userId);
        mav.addObject("user",user);
        return mav;
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            session.invalidate();
        }
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        return mav;
    }
}
