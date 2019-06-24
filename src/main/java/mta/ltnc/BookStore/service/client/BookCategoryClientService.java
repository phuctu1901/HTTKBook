package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.client.BookCateDto;
import mta.ltnc.BookStore.entity.BookCategory;
import mta.ltnc.BookStore.repositories.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryClientService {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    public List<BookCateDto> getAll(){
        return bookCategoryRepository.getAll();
    }
    public BookCategory findById(Long id){
        return bookCategoryRepository.findById(id).get();
    }
}
