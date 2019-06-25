package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.repositories.BookRepository;
import mta.ltnc.BookStore.repositories.CategoryRepository;
import mta.ltnc.BookStore.repositories.OrderRepository;
import mta.ltnc.BookStore.repositories.UserRepository;
import mta.ltnc.BookStore.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminDashboardController {
    @Autowired
    private AdminCategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = {"/admin/dashboard","/admin"}, method = RequestMethod.GET)
    public ModelAndView dashboard() {
        Long category_count = categoryRepository.count();
        Long book_count = bookRepository.count();
        Long user_count = userRepository.count();
        Long order_count = orderRepository.count();
        ModelAndView modelAndView = new ModelAndView("admin/dashboard/index");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("category_count", category_count);
        modelAndView.addObject("user_count", user_count);
        modelAndView.addObject("order_count", order_count);
        modelAndView.addObject("book_count", book_count);


        return modelAndView;
    }
}
