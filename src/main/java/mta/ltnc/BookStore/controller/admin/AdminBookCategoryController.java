package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.entity.BookCategory;
import mta.ltnc.BookStore.entity.Category;
import mta.ltnc.BookStore.repositories.BookCategoryRepository;
import mta.ltnc.BookStore.service.admin.AdminBookCategoryService;
import mta.ltnc.BookStore.service.admin.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class AdminBookCategoryController {
    @Autowired
    private AdminBookCategoryService bookCategoryService;

    @Autowired
    private AdminCategoryService categoryService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;
//    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
//    public ModelAndView listAllCategory() {
//        ModelAndView modelAndView = new ModelAndView("admin/book_category/index");
//        modelAndView.addObject("book_categories", bookCategoryService.findAll());
//        System.out.println(bookCategoryService.findAll());
//        return modelAndView;
//    }

    @RequestMapping(value = "/admin/bookcategory", method = RequestMethod.GET)
    public ModelAndView listCategory(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size
    ) {
        int currentPage = 1;
        int pageSize = 5;
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

    @RequestMapping(value = "/admin/bookcategory", method = RequestMethod.POST)
    public ModelAndView tableCategory(
            @RequestParam("page") int page,  @RequestParam("size") int size) {
        int currentPage = page;
        int pageSize = size;
        Page<BookCategory> bookCategoryPage = bookCategoryService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        ModelAndView modelAndView = new ModelAndView("admin/book_category/index::data_table");
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

    @RequestMapping(value = "/admin/bookcategory/add", method = RequestMethod.GET)
    public ModelAndView addBookCategoryView(){
        List<Category> categories = categoryService.findAll();
        BookCategory bookCategory = new BookCategory();
        ModelAndView modelAndView = new ModelAndView("admin/book_category/add");
        modelAndView.addObject("addBookCategory",bookCategory);
        modelAndView.addObject("categories",categories );


        return modelAndView;
    }

    @RequestMapping(value="/admin/bookcategory/addRequest", method = RequestMethod.POST)
    public String addBookCategory(@Valid BookCategory bookCategory, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin/book_category/add";
        }

        System.out.print(bookCategory);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        bookCategory.setCreateDate(timestamp);
        bookCategory.setDisplayOrder(1);
        bookCategoryRepository.save(bookCategory);
        return "redirect:/admin/bookcategory";
    }

}
