package mta.ltnc.BookStore.controller.admin;

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

    @RequestMapping(value = {"/admin/dashboard","/admin"}, method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard/index");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
}
