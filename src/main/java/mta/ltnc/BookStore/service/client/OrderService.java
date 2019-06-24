package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.client.BookDto;
import mta.ltnc.BookStore.dto.client.OrderDetailDto;
import mta.ltnc.BookStore.dto.client.OrderDto;
import mta.ltnc.BookStore.repositories.BookImageRepository;
import mta.ltnc.BookStore.repositories.OrderDetailRepository;
import mta.ltnc.BookStore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private BookImageRepository bookImageRepository;
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
}
