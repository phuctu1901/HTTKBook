package mta.ltnc.BookStore.dto.client;

import mta.ltnc.BookStore.controller.client.HelpModelAndView;
import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.service.client.AccountService;
import mta.ltnc.BookStore.service.client.AuthorClientService;
import mta.ltnc.BookStore.service.client.CategoryClientService;
import mta.ltnc.BookStore.service.client.PublisherClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/rest")
public class AccRestController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorClientService authorService;
    @Autowired
    private PublisherClientService publisherService;
    @Autowired
    private CategoryClientService categoryService;


    @PostMapping(path = "/account/check-old-password",produces = "application/json;charset=UTF-8")
    public Boolean checkOldPassword(@Param("oldpassword")String oldpassword, HttpSession session){
        long userId = (long)session.getAttribute("userId");
        User u = accountService.findUser(userId);
        Boolean status = true;
        status = accountService.checkSamePassword(oldpassword,u.getPassword());
        return status;
    }

//    @PostMapping("/change-profile")
//    public ModelAndView changeProfilePost(@Valid @ModelAttribute("user")User user,
//                                          BindingResult result,
//                                          HttpSession session){
//        if (result.hasErrors()) {
//            return new ModelAndView("client/shared/error");
//        }
//        String message = "";
//        long userId = (long)session.getAttribute("userId");
//        User u = accountService.findUser(userId);
//        if (accountService.checkSamePassword(user.getPassword(),u.getPassword())) {
//
//            message = "Sửa thông tin tài khoản thành công!";
//            user.setCreatedDate(new Timestamp(new Date().getTime()));
//            user.setStatus(true);
//            user.setId(u.getId());
//            accountService.updateUser(user);
//        }
//        else {
//            message = "Mật khẩu nhập vào không đúng!";
//        }
//        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
//        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
//        mav.addObject("message",message);
//        return mav;
//    }

    @PostMapping("/change-password")
    public ModelAndView changePassword(@RequestParam("password_1")String pass1, HttpSession session){

        String message = "";
        long userId = (long)session.getAttribute("userId");

           User user = accountService.findUser(userId);

        accountService.updatePass(pass1,user);
        message = "Sửa mật khẩu thành công!";
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        mav.addObject("message",message);
        return mav;
    }


    @PostMapping("/change-profile")
    public ModelAndView changeProfilePost(@RequestParam("name")String name,@RequestParam("UserName")String UserName, @RequestParam("address")String address,  @RequestParam("email")String email, @RequestParam("phone")String phone, @RequestParam("password")String password,HttpSession session){

        String message = "";
        long userId = (long)session.getAttribute("userId");

        User user = accountService.findUser(userId);

        if (accountService.checkSamePassword(user.getPassword(),password)) {


            user.setCreatedDate(new Timestamp(new Date().getTime()));
            user.setStatus(true);
//            user.setId(u.getId());
            accountService.updateUser(user,name,UserName,address,email,phone);
            message = "Sửa thông tin tài khoản thành công!";
        }
        else {
            message = "Mật khẩu nhập vào không đúng!";
        }
        ModelAndView mav = new ModelAndView("client/home/redirect_home_index");
        HelpModelAndView.dataForLayout(mav,categoryService,publisherService,authorService,session);
        mav.addObject("message",message);
        return mav;
    }


}
