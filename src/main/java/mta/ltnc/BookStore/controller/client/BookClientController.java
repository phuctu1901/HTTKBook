package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.BookDto;
import mta.ltnc.BookStore.entity.BookCategory;
import mta.ltnc.BookStore.entity.Category;
import mta.ltnc.BookStore.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookClientController {
    @Autowired
    private BookClientService bookService;
    @Autowired
    private AuthorClientService authorService;
    @Autowired
    private PublisherClientService publisherService;
    @Autowired
    private CategoryClientService categoryService;
    @Autowired
    private BookCategoryClientService bookCategoryService;
//    @GetMapping("/list-book")
//    public ModelAndView listBook(){
//        List<BookDto> listBook = bookService.getAllDto();
//        ModelAndView mav = new ModelAndView("client/book/list_book");
//        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService);
//        mav.addObject("listBook", listBook);
//        mav.addObject("title","List Book");
//        return mav;
//    }
    @GetMapping("/detail")
    public ModelAndView detailBook(@RequestParam("id")Long id, HttpSession session){
        BookDto book = bookService.getOneDto(id);
        BookCategory bookcate = bookCategoryService.findById(book.getBookCategoryId());
        Category cate = bookcate.getCategory();
        List<BookDto> listSameAuthor = bookService.getSameAuthorDto(book.getAuthorId());
        List<BookDto> listSameCategory = bookService.getSameBookCategoryDto(bookcate.getId());
        ModelAndView mav = new ModelAndView("client/book/detail");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        mav.addObject("book", book);
        mav.addObject("bookcate", bookcate);
        mav.addObject("cate", cate);
        mav.addObject("listSameAuthor",listSameAuthor);
        mav.addObject("listSameCategory",listSameCategory);
        mav.addObject("title","Chi tiết" + book.getName());
        return mav;
    }
}
