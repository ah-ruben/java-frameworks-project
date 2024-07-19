package com.example.demo.controllers;


import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BuyNowController {
    @Autowired
    private ProductService productService;

    @GetMapping("/buyNow")
    public String buyNow(@RequestParam("productID") int theId, Model theModel) {
        Product findProduct = productService.findById(theId);
        if (findProduct.getInv() >= 1) {
            findProduct.setInv(findProduct.getInv() - 1);
            productService.save(findProduct);
            return "/buynowsuccess";
        } else {
            return "/buynowfailure";
        }
    }
}
