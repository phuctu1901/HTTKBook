package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.*;
import mta.ltnc.BookStore.service.client.AuthorService;
import mta.ltnc.BookStore.service.client.CategoryService;
import mta.ltnc.BookStore.service.client.PublisherService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class HelpModelAndView {
    public static void dataForLayout(ModelAndView mav,
                                     CategoryService categoryService,
                                     PublisherService publisherService,
                                     AuthorService authorService,
                                     HttpSession session){
        long local = 1;
        long forein = 2;
        int total_quantity = 0;
        if(session != null){
            List<CartItemDto> cart = (List<CartItemDto>)session.getAttribute("cart");
            total_quantity = cart == null ? 0 : cart.size();
        }
        List<CategoryDto> category = categoryService.getAll();
        mav.addObject("category",category);
        List<SpecialBookCategoryDto> special = new ArrayList<>();
        mav.addObject("special",special);
        List<AuthorDto> authorlocal = authorService.getAllByAuthorType(local);
        mav.addObject("authorlocal",authorlocal);
        List<AuthorDto> authorforgery = authorService.getAllByAuthorType(forein);;
        mav.addObject("authorforgery",authorforgery);
        List<PublisherDto> publisher = publisherService.getAll();
        mav.addObject("publisher",publisher);
        mav.addObject("total_quantity",total_quantity);
    }
}
