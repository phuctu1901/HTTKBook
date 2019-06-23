package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.entity.BookCategory;
import mta.ltnc.BookStore.service.admin.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

//    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
//    public ModelAndView listAllCategory() {
//        ModelAndView modelAndView = new ModelAndView("admin/book_category/index");
//        modelAndView.addObject("book_categories", bookCategoryService.findAll());
//        System.out.println(bookCategoryService.findAll());
//        return modelAndView;
//    }

    @RequestMapping(value = "/admin/bookcategory", method = RequestMethod.GET)
    public ModelAndView listCategory(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);

        Page<BookCategory> bookCategoryPage = bookCategoryService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        ModelAndView modelAndView = new ModelAndView("admin/book_category/index");
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


}
