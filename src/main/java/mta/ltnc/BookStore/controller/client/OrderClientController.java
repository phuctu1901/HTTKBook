package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.CartItemDto;
import mta.ltnc.BookStore.entity.Order;
import mta.ltnc.BookStore.entity.OrderDetail;
import mta.ltnc.BookStore.entity.ShippingType;
import mta.ltnc.BookStore.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderClientController {
    @Autowired
    private OrderClientService orderClientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorClientService authorService;
    @Autowired
    private PublisherClientService publisherService;
    @Autowired
    private CategoryClientService categoryService;
    @Autowired
    private ShippingTypeService shippingTypeService;
    @Autowired
    private  BookClientService bookClientService;
    @GetMapping("/check-out")
    public ModelAndView checkOut(HttpSession session) {
        if (session.getAttribute("cart") == null) {
            ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
            mav.addObject("message", "Hãy thêm vào giỏ hàng trước!");
            return mav;
        }
        Long userId = (Long) session.getAttribute("userId");
        if (session.getAttribute("order") == null && userId != null) {
            Order temp = orderClientService.createOrder(userId);
            session.setAttribute("order", temp);
        }
        if (session.getAttribute("order") == null && userId == null) {
            session.setAttribute("order", new Order());
        }
        Order order = (Order) session.getAttribute("order");
        ModelAndView mav = new ModelAndView("client/order/check_out");
        HelpModelAndView.dataForLayout(mav, categoryService, publisherService, authorService, session);
        HashMap<Long, CartItemDto> cart = (HashMap<Long, CartItemDto>) session.getAttribute("cart");
        Long totalPrice = new Long(0);
        Long realPrice = new Long(0);
        for (Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        }
        ;
        mav.addObject("totalPrice", totalPrice);
        mav.addObject("totalPromotion", totalPrice - realPrice);
        mav.addObject("total", realPrice);
        mav.addObject("realTotal", realPrice);
        return mav;
    }

    @PostMapping("/check-out")
    public ModelAndView checkOut(@ModelAttribute("order") Order o, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
            mav.addObject("message", "Hãy thêm vào giỏ hàng trước!");
            return mav;
        }
        if (session.getAttribute("order") == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out");
            return mav;
        }
        ModelAndView mav = new ModelAndView("redirect:/order/check-out-2");
        Order order = (Order) session.getAttribute("order");
        order.setShipName(o.getShipName());
        order.setShipMobile(o.getShipMobile());
        order.setShipEmail(o.getShipEmail());
        order.setShipAdress(o.getShipAdress());
        session.setAttribute("order", order);
        return mav;
    }

    @GetMapping("/check-out-2")
    public ModelAndView checkOut2(HttpSession session) {
        if (session.getAttribute("order") == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out");
            return mav;
        }
        Order order = (Order) session.getAttribute("order");
        ModelAndView mav = new ModelAndView("client/order/check_out_2");
        HelpModelAndView.dataForLayout(mav, categoryService, publisherService, authorService, session);
        HashMap<Long, CartItemDto> cart = (HashMap<Long, CartItemDto>) session.getAttribute("cart");
        Integer totalPrice = 0;
        Integer realPrice = 0;
        for (Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        }
        ;
        if (order.getShippingType() == null) {
            order.setShippingType(shippingTypeService.findById(new Long(1)));
        }
        List<ShippingType> shippingTypes = shippingTypeService.getAll();
        Integer shippingCost = order.getShippingType().getCost();
        mav.addObject("shippingTypes", shippingTypes);
        mav.addObject("totalPrice", totalPrice);
        mav.addObject("totalPromotion", totalPrice - realPrice);
        mav.addObject("total", realPrice);
        mav.addObject("shippingCost", shippingCost);
        mav.addObject("realPrice", realPrice + shippingCost);
        return mav;
    }

    @PostMapping("/check-out-2")
    public ModelAndView checkout2(@Param("shippingType") Long shippingType, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        ShippingType ship = shippingTypeService.findById(shippingType);
        order.setShippingType(ship);
        return new ModelAndView("redirect:/order/check-out-3");
    }

    @GetMapping("/check-out-3")
    public ModelAndView checkout3(HttpSession session) {
        if (session.getAttribute("order") == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out");
            return mav;
        }
        Order order = (Order) session.getAttribute("order");
        if (order.getShippingType() == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out-2");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/order/check_out_3");
        HelpModelAndView.dataForLayout(mav, categoryService, publisherService, authorService, session);
        HashMap<Long, CartItemDto> cart = (HashMap<Long, CartItemDto>) session.getAttribute("cart");
        Integer totalPrice = 0;
        Integer realPrice = 0;
        for (Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        }
        ;
        Integer shippingCost = order.getShippingType().getCost();
        mav.addObject("totalPrice", totalPrice);
        mav.addObject("totalPromotion", totalPrice - realPrice);
        mav.addObject("total", realPrice);
        mav.addObject("shippingCost", shippingCost);
        mav.addObject("realPrice", realPrice + shippingCost);
        return mav;
    }

    @PostMapping("/check-out-3")
    public ModelAndView checkOut3(@Param("paymentType") Long paymentType){
        return new ModelAndView("redirect:/order/check-out-4");
    }
    @GetMapping("/check-out-4")
    public ModelAndView checkOut4(HttpSession session){
        if (session.getAttribute("order") == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out");
            return mav;
        }
        Order order = (Order) session.getAttribute("order");
        if (order.getShippingType() == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out-2");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/order/check_out_4");
        HelpModelAndView.dataForLayout(mav, categoryService, publisherService, authorService, session);
        HashMap<Long, CartItemDto> cart = (HashMap<Long, CartItemDto>) session.getAttribute("cart");
        Integer totalPrice = 0;
        Integer realPrice = 0;
        for (Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        }
        Integer shippingCost = order.getShippingType().getCost();
        mav.addObject("totalPrice", totalPrice);
        mav.addObject("totalPromotion", totalPrice - realPrice);
        mav.addObject("total", realPrice);
        mav.addObject("shippingCost", shippingCost);
        mav.addObject("realPrice", realPrice + shippingCost);

        List<OrderDetail> orderDetail = new ArrayList<>();
        for (Long i : cart.keySet()){
            CartItemDto temp = cart.get(i);
            OrderDetail od = new OrderDetail();
            od.setBook(bookClientService.findById(temp.getItemId()));
            od.setPrice(temp.getPrice());
            od.setQuantity(temp.getQuantity());
            orderDetail.add(od);
        }
        mav.addObject("orderDetail",orderDetail);
        return mav;
    }
    @PostMapping("/check-out-4")
    public ModelAndView checkOut42(HttpSession session){
        if (session.getAttribute("order") == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out");
            return mav;
        }
        Order order = (Order) session.getAttribute("order");
        if (order.getShippingType() == null) {
            ModelAndView mav = new ModelAndView("redirect:/order/check-out-2");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        HashMap<Long, CartItemDto> cart = (HashMap<Long, CartItemDto>) session.getAttribute("cart");
        Integer totalPrice = 0;
        Integer realPrice = 0;
        for (Long i : cart.keySet()) {
            totalPrice = totalPrice + cart.get(i).getPrice();
            realPrice = realPrice + cart.get(i).getPromotion_price();
        }
        Integer shippingCost = order.getShippingType().getCost();
        order.setCreatedDate(new Timestamp(new Date().getTime()));
        order.setTotalPrice(realPrice+shippingCost);
        if(session.getAttribute("userId") != null){
            order.setUser(accountService.findUser((Long)session.getAttribute("userId")));
        }
        orderClientService.save(order);
        for (Long i : cart.keySet()){
            CartItemDto temp = cart.get(i);
            OrderDetail od = new OrderDetail();
            od.setBook(bookClientService.findById(temp.getItemId()));
            od.setOrder(orderClientService.findByCode(order.getCode()));
            od.setPrice(temp.getPrice());
            od.setQuantity(temp.getQuantity());
            orderClientService.saveOrderDetail(od);
        }
        mav.addObject("message","Đặt hàng thành công! Mã đơn hàng là: " + order.getCode());
        return mav;
    }
}
