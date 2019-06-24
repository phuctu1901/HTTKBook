package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.entity.Order;
import mta.ltnc.BookStore.repositories.BookRepository;
import mta.ltnc.BookStore.service.admin.*;
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
public class OrderController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin/order", method = RequestMethod.GET)
    public ModelAndView listAllOrder(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Order> data = orderService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        ModelAndView modelAndView = new ModelAndView("admin/order/index");
        modelAndView.addObject("page", data);


        int totalPages = data.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/order", method = RequestMethod.POST)
    public ModelAndView getAllOrder(
            @RequestParam("page") int page,  @RequestParam("size") int size) {
        int currentPage = page  ;
        int pageSize = size ;

        Page<Order> data = orderService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        ModelAndView modelAndView = new ModelAndView("admin/order/index::data_table");
        modelAndView.addObject("page", data);


        int totalPages = data.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }

//    @RequestMapping(value = "/admin/book/add", method = RequestMethod.GET)
//    public ModelAndView addBookView(){
//        Book addBook = new Book();
//        List<BookCategory> bookCategories = bookCategoryService.findAll();
//        List<Author> authors = authorService.findAll();
//        List<Publisher> publishers = publisherService.findAll();
//        ModelAndView modelAndView = new ModelAndView("admin/book/add");
//        modelAndView.addObject("addBook",addBook);
//        modelAndView.addObject("bookcategories",bookCategories);
//        modelAndView.addObject("authors",authors);
//        modelAndView.addObject("publishers",publishers);
//
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value="/admin/book/addRequest", method = RequestMethod.POST)
//    public String addBookCategory(@Valid Book book, BindingResult result) {
////        if (result.hasErrors()) {
////            return "redirect:/admin/book/add";
////        }
//
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        book.setCreateDate(timestamp);
////        book.set;
//        bookRepository.save(book);
//        return "redirect:/admin/book";
//    }


}
