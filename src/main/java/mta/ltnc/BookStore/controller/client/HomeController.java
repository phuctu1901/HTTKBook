package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.AccountDto;
import mta.ltnc.BookStore.service.client.AuthorService;
import mta.ltnc.BookStore.service.client.CategoryService;
import mta.ltnc.BookStore.service.client.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/test")
    public ModelAndView manageCategory(HttpSession session) {
        AccountDto acc = new AccountDto();
        ModelAndView mav = new ModelAndView("client/shared/index2");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        return mav;
    }
    @GetMapping("/client")
    public ModelAndView clientindex(HttpSession session) {
        AccountDto acc = new AccountDto();
        ModelAndView mav = new ModelAndView("client/home/index");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        mav.addObject("title","Trang chủ");
        return mav;
    }
}
