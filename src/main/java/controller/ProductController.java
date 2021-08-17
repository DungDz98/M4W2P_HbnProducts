package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Value("${save-img}")
    private String fileUpload;

    @GetMapping("/")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("show");
        productService.findAll();
        modelAndView.addObject("listProduct", productService.list);
        return modelAndView;
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int index) {
        productService.delete(index);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/create")
    public ModelAndView showCreate() {
        return new ModelAndView("create", "product", new Product());
    }
    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int index) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", productService.list.get(index));
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@RequestParam MultipartFile upImg,@ModelAttribute Product product) throws IOException {
        String nameImg = upImg.getOriginalFilename();
        FileCopyUtils.copy(upImg.getBytes(), new File(fileUpload + nameImg));
        product.setImg("/img/" + nameImg);
        productService.save(product);
        return new ModelAndView("redirect:/");
    }
    @PostMapping("/edit")
    public ModelAndView edit(@RequestParam MultipartFile upImg,@ModelAttribute Product product) throws IOException {
        String nameImg = upImg.getOriginalFilename();
        FileCopyUtils.copy(upImg.getBytes(), new File(fileUpload + nameImg));
        product.setImg("/img/" + nameImg);
        productService.edit(product);
        return new ModelAndView("redirect:/");
    }
}
