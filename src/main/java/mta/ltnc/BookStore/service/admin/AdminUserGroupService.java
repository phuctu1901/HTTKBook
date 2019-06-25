package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.UserGroup;
import mta.ltnc.BookStore.repositories.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminUserGroupService {
    @Autowired
    private UserGroupRepository repo;

    @Autowired
    private AdminCategoryService service;

    public List<UserGroup> findAll(){
        return repo.findAll();
    }

    public Page<UserGroup> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserGroup> list;
        List<UserGroup> data = repo.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<UserGroup> page
                = new PageImpl<UserGroup>(list, PageRequest.of(currentPage, pageSize), data.size());
        return page;
    }
}
