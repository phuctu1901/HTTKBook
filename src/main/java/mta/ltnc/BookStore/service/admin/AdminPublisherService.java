package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.Publisher;
import mta.ltnc.BookStore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminPublisherService {
    @Autowired
    private PublisherRepository repo;

    @Autowired
    private AdminCategoryService service;

    public List<Publisher> findAll(){
        return repo.findAll();
    }

    public Page<Publisher> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Publisher> list;
        List<Publisher> data = repo.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<Publisher> page
                = new PageImpl<Publisher>(list, PageRequest.of(currentPage, pageSize), data.size());
        return page;
    }
}
