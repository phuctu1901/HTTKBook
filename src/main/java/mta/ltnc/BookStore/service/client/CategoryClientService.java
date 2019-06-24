package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.client.BookCateDto;
import mta.ltnc.BookStore.dto.client.CategoryDto;
import mta.ltnc.BookStore.entity.Category;
import mta.ltnc.BookStore.repositories.BookCategoryRepository;
import mta.ltnc.BookStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryClientService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    public List<CategoryDto> getAll(){
        List<CategoryDto> ls = categoryRepository.getAllWithouListBookCate();
        ls.forEach((categoryDto -> {
            List<BookCateDto> lsbc = bookCategoryRepository.getAllByCategory(categoryDto.getCategory());
            categoryDto.setLsBookCategory(lsbc);
        }));
        return ls;
    }
    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }
}
