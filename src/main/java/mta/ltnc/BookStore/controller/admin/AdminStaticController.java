package mta.ltnc.BookStore.controller.admin;

import mta.ltnc.BookStore.dto.admin.AdminOrderDTO;
import mta.ltnc.BookStore.entity.StatusOrder;
import mta.ltnc.BookStore.repositories.OrderRepository;
import mta.ltnc.BookStore.repositories.StatusOrderRepository;
import mta.ltnc.BookStore.service.admin.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminStaticController {
    @Autowired
    private AdminCategoryService categoryService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @RequestMapping(value = {"/admin/static"}, method = RequestMethod.GET)

    public ModelAndView staticview() {
        List<StatusOrder> statusOrderList= statusOrderRepository.findAll();
        List<AdminOrderDTO> adminOrderDTOList = new ArrayList<>();

        for (StatusOrder statusOrder: statusOrderList){
            AdminOrderDTO adminOrderDTO = new AdminOrderDTO();

            Long tmp = orderRepository.countAllByStatusOrder(statusOrder);
            adminOrderDTO.setName(statusOrder.getName());
            adminOrderDTO.setAmount(tmp);
            adminOrderDTOList.add(adminOrderDTO);
        }

        ModelAndView modelAndView = new ModelAndView("admin/static/index");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("orders", adminOrderDTOList);
        return modelAndView;
    }
}
