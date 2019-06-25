package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.AuthorType;
import mta.ltnc.BookStore.repositories.AuthorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminAuthorTypeService {
    @Autowired
    private AuthorTypeRepository repo;

    @Autowired
    private AdminCategoryService service;

    public List<AuthorType> findAll(){
        return repo.findAll();
    }

    public Page<AuthorType> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AuthorType> list;
        List<AuthorType> data = repo.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<AuthorType> page
                = new PageImpl<AuthorType>(list, PageRequest.of(currentPage, pageSize), data.size());
        return page;
    }
}
