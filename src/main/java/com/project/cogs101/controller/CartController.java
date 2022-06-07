package com.project.cogs101.controller;


import com.project.cogs101.domain.Order;
import com.project.cogs101.domain.Product;
import com.project.cogs101.service.OrderService;
import com.project.cogs101.service.ProductService;
import com.project.cogs101.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Map;


@Controller
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductService productService, OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.removeProduct(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart(){
        shoppingCartService.clearProducts();

        return "redirect:/cart";
    }

    @GetMapping("/cart/addA/{id}")
    private String amountAdd(@PathVariable("id") long id){
        for (Map.Entry<Product, Integer> product : shoppingCartService.productsInCart().entrySet()){
            if(product.getKey().getId() == id){
                shoppingCartService.productsInCart().replace(product.getKey(), product.getValue(), product.getValue()+1);
                break;
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/subtractA/{id}")
    private String amountSub(@PathVariable("id") long id){
        for (Map.Entry<Product, Integer> product : shoppingCartService.productsInCart().entrySet()){
            if(product.getKey().getId() == id){
                if( product.getValue() > 0){
                    shoppingCartService.productsInCart().replace(product.getKey(), product.getValue(), product.getValue()-1);
                }
                break;
            }
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout/")
    public String cartCheckout(Principal principal){
        for(Map.Entry<Product, Integer> product : shoppingCartService.productsInCart().entrySet()){
            orderService.save(new Order(product.getKey().getName(),product.getValue(),java.time.LocalDate.now().toString(),principal.getName()));
        }

        shoppingCartService.cartCheckout();

        return "redirect:/cart";
    }
}
