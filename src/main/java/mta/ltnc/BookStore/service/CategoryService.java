package mta.ltnc.BookStore.service;

import mta.ltnc.BookStore.dto.ResponseDto;
import mta.ltnc.BookStore.entity.Category;
import mta.ltnc.BookStore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseDto getAll() {
        return new ResponseDto("0", "Success", categoryRepository.findAll());
    }
    public  List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
