package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.client.BookDto;
import mta.ltnc.BookStore.dto.client.OrderDetailDto;
import mta.ltnc.BookStore.dto.client.OrderDto;
import mta.ltnc.BookStore.entity.Order;
import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderClientService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private BookImageRepository bookImageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    public List<OrderDto> findAllByUserId(Long userId){
        List<OrderDto> ls = orderRepository.findAllByUserId(userId);
        Long num = Long.parseLong("1");
        for (OrderDto x : ls) {
            x.setNumber(num);
            num++;
        }
        return ls;
    }
    public List<OrderDetailDto> findAllOrderDetailByOrderId(Long orderId){
        List<OrderDetailDto> ls = orderDetailRepository.getAllByOrderId(orderId);
        Long num = Long.parseLong("1");
        for (OrderDetailDto x : ls) {
            BookDto temp = x.getBookDto();
            temp.setListImages(bookImageRepository.getAllByBook(x.getBookDto().getId()));
            x.setBookDto(temp);
            x.setNumber(num);
            num++;
        }
        return ls;
    }
    public Order createOrder(Long userId){
        Order order = new Order();
        User user = userRepository.findById(userId).get();
        order.setCreatedDate(new Timestamp(new Date().getTime()));
        order.setUser(user);
        order.setStatusOrder(statusOrderRepository.findById(2).get());
        order.setShipAdress(user.getAddress());
        order.setShipEmail(user.getEmail());
        order.setShipMobile(user.getPhone());
        return order;
    }
}
