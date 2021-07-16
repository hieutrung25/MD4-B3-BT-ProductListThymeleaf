package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ModelAndView modelAndView;

    @GetMapping
    public ModelAndView showProductList() {
        List<Product> productList = productService.findAll();
        modelAndView.setViewName("list");
        modelAndView.addObject("productList", productList);
        System.out.println(productList);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        Product product = productService.findOne(id);
        modelAndView.setViewName("detail");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/detail/{id}")
    public RedirectView actionHandler(@ModelAttribute("product") Product product,
                                      @RequestParam("action") String action,
                                      RedirectAttributes attributes) {
        switch (action) {
            case "update":
                productService.update(product.getId(), product);
                attributes.addFlashAttribute("message", "Cập nhật sản phẩm thành công");
                break;
            case "remove":
                productService.remove(product.getId());
                attributes.addFlashAttribute("message", "Xóa sản phẩm thành công");
                break;
            default:
        }
        return new RedirectView("/");
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        modelAndView.setViewName("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView saveProduct(@ModelAttribute("product") Product product, RedirectAttributes attributes) {
        productService.save(product);
        attributes.addFlashAttribute("message", "Thêm sản phẩm thành công");
        return new RedirectView("/");
    }

}