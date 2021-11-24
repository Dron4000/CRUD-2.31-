package com.javaspring.controller;

import com.javaspring.ProductService.ProductService;
import com.javaspring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class AppController {

    @Autowired
   private ProductService productService;


    @GetMapping(value = "/")
    public String viewHomePage(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        return "index";
    }

    @GetMapping(value = "/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping (value = "/save",method = {RequestMethod.GET,RequestMethod.POST})
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView showEditProductPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.getProductById( id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String deleteProduct(@PathVariable(name = "id") long id) {
        productService.deleteProductById( id);
        return "redirect:/";
    }


}
