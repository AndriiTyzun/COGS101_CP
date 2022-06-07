package com.project.cogs101.controller;

import com.project.cogs101.domain.Order;
import com.project.cogs101.domain.Product;
import com.project.cogs101.domain.User;
import com.project.cogs101.service.OrderService;
import com.project.cogs101.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = {"/user","/admin"})
    public String userPanel(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("orders", getAllOrders());
            model.addAttribute("userOrders", getUserOrders(principal.getName()));
        }else {
            return "error/404";
        }

        return "user";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") long productId){
        Order order = orderService.findById(productId);
        if (order != null){
            orderService.delete(productId);
            return "redirect:/admin";
        }else {
            return "error/404";
        }
    }

    private List<Order> getAllOrders(){
        System.out.println( orderService.findAllByOrderByIdAsc().size());
        return orderService.findAllByOrderByIdAsc();
    }
    private List<Order> getUserOrders(String username){
        return orderService.findAllByUsername(username);
    }
}
