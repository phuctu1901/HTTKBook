package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.service.admin.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/admin/book", method = RequestMethod.GET)
    public ModelAndView listAllBook() {
        ModelAndView modelAndView = new ModelAndView("admin/book/index");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }
}
