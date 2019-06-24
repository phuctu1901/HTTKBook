package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    private UserRepository userRepository;

    public  List<User> findAll(){
        return userRepository.findAll();
    }
    public Page<User> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;
        List<User> data = userRepository.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<User> bookCategoryPage
                = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), data.size());

        return bookCategoryPage;
    }

    public Page<User> search(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;
        List<User> data = userRepository.findAll();
        if (data.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, data.size());
            list = data.subList(startItem, toIndex);
        }

        Page<User> bookCategoryPage
                = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), data.size());

        return bookCategoryPage;
    }
}
