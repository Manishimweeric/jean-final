package net.mycampany.myWEBAPPStudy.Controller;


import jakarta.servlet.http.HttpSession;
import net.mycampany.myWEBAPPStudy.Model.Cart;
import net.mycampany.myWEBAPPStudy.Model.Product;
import net.mycampany.myWEBAPPStudy.service.CartService;
import net.mycampany.myWEBAPPStudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;


    @Autowired
    private CartService cartService;


    @PostMapping("/cat_add_db")
    public String catstore(@RequestParam("id") Integer productId,
                           @RequestParam("quantity") Integer quantity,
                           Model model
    ) {

        Product product = productService.getProductById(productId);
        float totalAmount = quantity * product.getUnitPrice();

        Product data=new Product();
        data.setId(productId);

        Cart cartItem =new Cart();
        cartItem.setProduct(data);
        cartItem.setQuantity(quantity);
        cartItem.setTotal(totalAmount);

        cartService.registercart(cartItem);
        return "redirect:/home";
    }
    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cart");
        List<Cart> listcat=cartService.allcats();
        session.setAttribute("cart", listcat);
        model.addAttribute("carts", listcat);
        return "view-cats-form";
    }

    @GetMapping("/remove-from-cart{id}")
    public String removeFromCart(@RequestParam("id") Integer productId) {
        cartService.deleteItem(productId);
        return "redirect:/cart";
    }

}
