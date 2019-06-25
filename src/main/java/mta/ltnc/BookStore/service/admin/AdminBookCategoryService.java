package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.BookCategory;
import mta.ltnc.BookStore.repositories.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminBookCategoryService {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private AdminBookCategoryService bookCategoryService;

    public  List<BookCategory> findAll(){
        return bookCategoryRepository.findAll();
    }
    public Page<BookCategory> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BookCategory> list;
        List<BookCategory> bookCategories = bookCategoryRepository.findAll();
        if (bookCategories.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, bookCategories.size());
            list = bookCategories.subList(startItem, toIndex);
        }

        Page<BookCategory> bookCategoryPage
                = new PageImpl<BookCategory>(list, PageRequest.of(currentPage, pageSize), bookCategories.size());

        return bookCategoryPage;
    }

    public Page<BookCategory> search(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BookCategory> list;
        List<BookCategory> bookCategories = bookCategoryRepository.findAll();
        if (bookCategories.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, bookCategories.size());
            list = bookCategories.subList(startItem, toIndex);
        }

        Page<BookCategory> bookCategoryPage
                = new PageImpl<BookCategory>(list, PageRequest.of(currentPage, pageSize), bookCategories.size());

        return bookCategoryPage;
    }
}
