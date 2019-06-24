package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.Order;
import mta.ltnc.BookStore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;

    @Autowired
    private CategoryService service;

    public List<Order> findAll(){
        return repo.findAll();
    }

    public Page<Order> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> list;
        List<Order> data = repo.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<Order> page
                = new PageImpl<Order>(list, PageRequest.of(currentPage, pageSize), data.size());
        return page;
    }
}