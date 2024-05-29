package net.mycampany.myWEBAPPStudy.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import net.mycampany.myWEBAPPStudy.Model.Cart;
import net.mycampany.myWEBAPPStudy.Model.Order;
import net.mycampany.myWEBAPPStudy.service.CartService;
import net.mycampany.myWEBAPPStudy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping("/Ordersave")
    public String saveOrderFromCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cart");
        if (cartItems == null || cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        Order order = orderService.createOrderFromCart(cartItems);

        List<Cart> updatedCartItems = cartService.updateCartItems(cartItems);

        // Optionally, set the updated cart items back in the session if needed
        session.setAttribute("cart", updatedCartItems);


        session.removeAttribute("cart");
        System.out.println("Received cart items: " + cartItems);
        return "order-confirmation";
    }

    @GetMapping("/displayOrder")
    public  String displays(Model model) {
        List<Order> order_data=orderService.display_data();
        model.addAttribute("orderlist",order_data);
        return "view_order_from";
    }
    @GetMapping("/Update_approval{id}")
    public String approveOrder(@RequestParam("id")  Integer id) {
         orderService.approveOrder(id);
        return "redirect:/displayOrder";
    }

    @GetMapping("/Update_deny{id}")
    public String denyOrder(@RequestParam("id")  Integer id) {
         orderService.denyOrder(id);
        return "redirect:/displayOrder";
    }
    @GetMapping("/alldisplay")
    public  String alldisplays(Model model) {
        List<Order> order_data=orderService.allorderlist();
        model.addAttribute("all_order",order_data);
        return "view_all_order";
    }
    @GetMapping("/allusers")
    public  String alldisplaystouser(Model model) {
        List<Order> order_data=orderService.allorderlist();
        model.addAttribute("all_order_user",order_data);
        return "user_all_order";
    }




}
