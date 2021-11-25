package com.epam.brest.web_app;

import com.epam.brest.model.Order;
import com.epam.brest.service.OrderDtoService;
import com.epam.brest.service.OrderService;
import com.epam.brest.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OrderController {

    private final Logger logger = LogManager.getLogger(OrderController.class);

    private final OrderDtoService orderDtoService;

    private final OrderService orderService;

    public OrderController(OrderDtoService orderDtoService, OrderService orderService) {
        this.orderDtoService = orderDtoService;
        this.orderService = orderService;
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
        logger.debug("gotoEditOrderPage(id:{},model:{})", id, model);
        model.addAttribute("isNew", false);
        model.addAttribute("order", orderService.getOrderById(id));
        return "order";
    }

    /**
     * Goto new order page
     *
     * @return view name
     */
    @GetMapping(value = "/order")
    public final String gotoAddOrderPage(Model model) {
        logger.debug("gotoAddOrderPage({}))",model);
        model.addAttribute("isNew", true);
        model.addAttribute("order", new Order());
        return "order";
    }

    /**
     * Persist new order into persistence base
     *
     * @param order new order with filled data
     * @return view name
     */
    @PostMapping(value = "/order")
    public String addOrder(Order order) {
        logger.debug("addOrder({},{})", order); // Внимание!!!
        this.orderService.create(order);
        return "redirect:/orders";
    }

    /**
     * Update order.
     *
     * @param order order with filled data.
     * @return view name
     */
    @PostMapping(value = "/order/{id}")
    public String updateOrder(Order order) {

        logger.debug("updateOrder({}, {})", order);
        this.orderService.update(order);
        return "redirect:/orders";
    }

    /**
     * Delete order.
     *
     * @return view name
     */
    @GetMapping(value = "/order/{id}/delete")
    public final String deleteOrderById(@PathVariable Integer id, Model model) {

        logger.debug("delete({},{})", id, model);
        orderService.delete(id);
        return "redirect:/orders";
    }

}
