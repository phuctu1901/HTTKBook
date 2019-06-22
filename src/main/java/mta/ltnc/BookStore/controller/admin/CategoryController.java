package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public ModelAndView listAllCategory() {
        ModelAndView modelAndView = new ModelAndView("index3.html");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
}
