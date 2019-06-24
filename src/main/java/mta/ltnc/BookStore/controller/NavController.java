package mta.ltnc.BookStore.controller;

import mta.ltnc.BookStore.constant.AuthoritiesConstants;
import mta.ltnc.BookStore.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

    @GetMapping("/")
    public String mainnavcontroller() {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            return "redirect:/admin/dashboard";
        } else {
            if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SALE)) {
                return "redirect:/employee/sale";
            } else {
                if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.WAREHOUSE)) {
                    return "redirect:/employee/warehouse";
                } else
                    return "index";
            }
        }
    }

//    @GetMapping("/login")
//    public String login() {
//        return "common/login";
//    }

    @GetMapping("/password")
    public String password() {
        return "common/password";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "common/403";
    }

    @GetMapping("profile")
    public String profile() {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            return "redirect:/admin/index";
        } else {
            if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SALE)) {
                return "redirect:/employee/sale";
            } else {
                if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.WAREHOUSE)) {
                    return "redirect:/employee/warehouse";
                } else
                    return "common/profile";
            }
        }
    }

//    @GetMapping("/init-data")
//    public String initData() {
//        initialService.initData();
//        return "index";
//    }

//    //--------------------------ADMIN------------------------------
//    @GetMapping("/admin/dashboard")
//    public String dashboard() {
//        return "admin/dashboard";
//    }
//
//    @GetMapping("/admin/account")
//    public String manageAccount() {
//        return "admin/account";
//    }
//
//    @GetMapping("/admin/category")
//    public String manageCategory() {
//        return "admin/category";
//    }
//
//    @GetMapping("/admin/book")
//    public String manageBook() {
//        return "/admin/book";
//    }
//
//    @GetMapping("/admin/book-quantity")
//    public String manageBookQuantity() {
//        return "/admin/book-quantity";
//    }
//
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping("/admin/bill")
//    public String manageBill() {
//        return "/admin/bill";
//    }
//
//    //------------------------EMPLOYEE-----------------------------
//    @GetMapping("/employee/sale")
//    public String sale(Model model) {
//        EmployeeAccountDto dto = accountService.getCurrentEmployee();
//        model.addAttribute("user", dto.getName());
//        model.addAttribute("sachList", bookService.getByBookStore(dto.getBookStoreId()));
//        model.addAttribute("customerList", customerService.getAllCustomer());
//        model.addAttribute("store", dto.getStore());
//        return "/employee/sale";
//    }
//
//    @GetMapping("/employee/history")
//    public String historyEmployee(Model model) {
//        EmployeeAccountDto dto = accountService.getCurrentEmployee();
//        model.addAttribute("user", dto.getName());
//        model.addAttribute("store", dto.getStore());
//
//        return "/employee/history";
//    }
//
//    @GetMapping("/history")
//    public String history() {
//        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
//            return "redirect:/admin/dashboard";
//        } else {
//            if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.EMPLOYEE)) {
//                return "redirect:/employee/sale";
//            } else {
//                if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CHECKER)) {
//                    return "redirect:/checker";
//                } else
//                    return "customer/history";
//            }
//        }
//    }
//
//    @GetMapping("/checker")
//    public String checker() {
//        return "/checker/checker";
//    }
//
//    @GetMapping("/order/success/{id}")
//    public String orderSuccess(@PathVariable Long id) {
//        if (!buyRepository.findById(id).isPresent()) {
//            return "/common/404";
//        }
//        return "common/order-success";
//    }
//
//    @GetMapping("/order")
//    public String order() {
//        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
//            return "redirect:/admin/dashboard";
//        } else {
//            if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.EMPLOYEE)) {
//                return "redirect:/employee/sale";
//            } else {
//                if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CHECKER)) {
//                    return "redirect:/checker";
//                } else
//                    return "customer/order";
//            }
//        }
//    }
//
//    @GetMapping("/register")
//    public String register() {
//        return "customer/register";
//    }

//    @GetMapping("/book/{id}")
//    public String bookDetail(@PathVariable Long id) {
//        return "/book/book-detail";
//    }

}
