package net.mycampany.myWEBAPPStudy.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import net.mycampany.myWEBAPPStudy.Model.Category;
import net.mycampany.myWEBAPPStudy.Model.Product;
import net.mycampany.myWEBAPPStudy.service.CategoryService;
import net.mycampany.myWEBAPPStudy.service.OrderService;
import net.mycampany.myWEBAPPStudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService  categoryService;

    private List<List<Object>> cart = new ArrayList<>();

    @GetMapping("/product")
    public  String showproductform( Model model){
        List<Category> categories = categoryService.AllCategories();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "product-form";
    }

    @PostMapping("/product/save")
    public String saveProductList(@ModelAttribute("product") @Validated Product product,
                                  @RequestParam("imagefile") MultipartFile imagefile,
                                  Model model,
                                  @RequestParam("category_id") Integer categoryId) {
        if (!imagefile.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/images/";
                String originalFileName = imagefile.getOriginalFilename();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                Files.copy(imagefile.getInputStream(), Paths.get(uploadDir, uniqueFileName), StandardCopyOption.REPLACE_EXISTING);
                product.setImage(uniqueFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        productService.AddProduct(product);
        model.addAttribute("message4", "Product Added Success");
        return "redirect:/product";
    }


    @GetMapping("/productdata")
    public String listProducts(Model model) {
        List<Product> products = productService.Allproduct();
        for (Product product : products) {
            String imagePath = "images/" + product.getImage();
            product.setImage(imagePath);
        }
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/")
    public String cardProducts(Model model) {
        List<Product> products = productService.Allproduct();
        for (Product product : products) {
            String imagePath = "images/" + product.getImage();
            product.setImage(imagePath);
        }
        model.addAttribute("products_data", products);
        return "index";
    }


    @GetMapping("/home")
    public String showhome(Model model) {
        List<Product> productsS = productService.Allproduct();
        for (Product product : productsS) {
            String imagePath = "images/" + product.getImage();
            product.setImage(imagePath);
        }
        model.addAttribute("products_data_X", productsS);
        return "home";
    }



    @GetMapping("/add-to-cart{id}")
    public String addToCart(@RequestParam("id") Integer productId,Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("data",product);
        return "cart-form";
    }


    @GetMapping("/editproduct{id}")
    public String editProduct(@RequestParam("id") Integer id, Model model) {
        Product productOptional = productService.getiid(id);
            model.addAttribute("products", productOptional);
            return "edit_product";
    }
     @GetMapping("/Deleteproduct{id}")
        public String deleteProduct(@RequestParam("id") Integer id, Model model) {
            productService.deleteProductById(id);
             return "redirect:/productdata";
}
    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @ModelAttribute("products") Product product, Model model) {
        productService.updateProduct(id, product);
        return "redirect:/productdata";
    }
}
