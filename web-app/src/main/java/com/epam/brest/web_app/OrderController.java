package com.epam.brest.web_app;

import com.epam.brest.service.OrderDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class OrderController {
    private final OrderDtoService orderDtoService;

    public OrderController(OrderDtoService orderDtoService) {
        this.orderDtoService = orderDtoService;
    }

    /**
     * Goto orders list page
     *
     * @return view name
     */
    @GetMapping(value = "/orders")
    public final String orders(Model model) {
        model.addAttribute("orders", orderDtoService.findAllWithTotalPrice());
        return "orders";
    }

    /**
     * Goto edit order page
     *
     * @return view name
     */
    @GetMapping(value = "/order/{id}")
    public final String gotoEditOrderPage(@PathVariable Integer id, Model model) {
        return "order";
    }

    /**
     * Goto new order page
     *
     * @return view name
     */
    @GetMapping(value = "/order/add")
    public final String gotoAddOrderPage(Model model) {
        return "order";
    }

}
