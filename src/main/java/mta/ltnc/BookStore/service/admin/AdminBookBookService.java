package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.Book;
import mta.ltnc.BookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminBookBookService {
    @Autowired
    private BookRepository repo;

    @Autowired
    private AdminCategoryService service;

    public List<Book> findAll(){
        return repo.findAll();
    }

    public Page<Book> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;
        List<Book> data = repo.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<Book> page
                = new PageImpl<Book>(list, PageRequest.of(currentPage, pageSize), data.size());
        return page;
    }
}
