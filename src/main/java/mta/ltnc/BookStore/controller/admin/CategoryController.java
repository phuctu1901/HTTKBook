package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.entity.Category;
import mta.ltnc.BookStore.repositories.CategoryRepository;
import mta.ltnc.BookStore.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class CategoryController {
    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public ModelAndView listCategory(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(20);

        Page<Category> bookCategoryPage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        ModelAndView modelAndView = new ModelAndView("admin/category/index");
        modelAndView.addObject("page", bookCategoryPage);


        int totalPages = bookCategoryPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/admin/category/add", method = RequestMethod.GET)
    public ModelAndView addCategoryView(){
        Category category = new Category();
        ModelAndView modelAndView = new ModelAndView("admin/category/add");
        modelAndView.addObject("addCategory",category );

        return modelAndView;
    }

    @RequestMapping(value="/admin/category/addRequest", method = RequestMethod.POST)
//    public String addCategoryRequest(){
//        ModelAndView modelAndView = new ModelAndView("admin/category/index");
//        return "redirect:/admin/category";
//    }
    public String addUser(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin/category/add";
        }

        System.out.print(category);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        category.setCreatedDate(timestamp);
        category.setDisplayOrder(1);
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }


}
