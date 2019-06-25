package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.Category;
import mta.ltnc.BookStore.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.List;

@Service
public class AdminCategoryService {
    @Autowired
    private CategoryRepository repo;

    @Autowired
    private AdminCategoryService service;

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Category> list;
        List<Category> data = repo.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<Category> page
                = new PageImpl<Category>(list, PageRequest.of(currentPage, pageSize), data.size());

        return page;
    }
}
