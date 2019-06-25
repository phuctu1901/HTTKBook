package mta.ltnc.BookStore.controller.client;

import mta.ltnc.BookStore.dto.client.*;
import mta.ltnc.BookStore.service.client.AuthorClientService;
import mta.ltnc.BookStore.service.client.CategoryClientService;
import mta.ltnc.BookStore.service.client.PublisherClientService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpModelAndView {
    public static void dataForLayout(ModelAndView mav,
                                     CategoryClientService categoryService,
                                     PublisherClientService publisherService,
                                     AuthorClientService authorService,
                                     HttpSession session){
        long local = 1;
        long forein = 2;
        int totalQuantity = 0;
        if(session != null){
            HashMap<Long,CartItemDto> cart = (HashMap<Long,CartItemDto>)session.getAttribute("cart");
            totalQuantity = cart == null ? 0 : cart.size();
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
        mav.addObject("totalQuantity",totalQuantity);
    }

}
