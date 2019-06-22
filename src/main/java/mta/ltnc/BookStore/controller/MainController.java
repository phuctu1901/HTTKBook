package mta.ltnc.BookStore.controller;
import mta.ltnc.BookStore.service.BookCategoryService;
import mta.ltnc.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import mta.ltnc.BookStore.service.CategoryService;
import  mta.ltnc.BookStore.dto.*;

@RestController
public class MainController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService bookCategoryService;


    @GetMapping("/admin/category")
    public String manageCategory() {
        return "admin/category";
    }

    @GetMapping("/test")
    public ResponseDto testCategory() {

        return categoryService.getAll();

//        return "test";
    }

    @GetMapping("/test2")
    public ResponseDto testBook() {

        return bookService.getAll();

//        return "test";
    }

    @GetMapping("/test3")
    public ResponseDto testBookCategory() {

        return bookCategoryService.getAll();

//        return "test";
    }
}
