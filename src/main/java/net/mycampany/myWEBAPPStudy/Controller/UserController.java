package net.mycampany.myWEBAPPStudy.Controller;

import jakarta.servlet.http.HttpSession;
import net.mycampany.myWEBAPPStudy.service.UserService;
import net.mycampany.myWEBAPPStudy.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //to register a  User Account

    @GetMapping("/register")
    public String showuserform(Model model) {
        return "user_form";
    }

    @GetMapping("/success")
    public String showsuccessform(Model model) {
        return "order-confirmation";
    }


    @GetMapping("/admindash")
    public String showadmindash(Model model) {
        return "admindashboard";
    }

    @GetMapping("/userdash")
    public String showuserdash(Model model) {
        return "userdashbord";
    }

    @GetMapping("/login")
    public String showloginform(Model model) {
        return "login_form";
    }

    @PostMapping("/user/save")
    public String saveuser(User user, RedirectAttributes ra, Model model) {
        userService.save(user);
        model.addAttribute("message", "The user has been  saved successfully");
        return "user_form";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, HttpSession session,Model model) {

        // Attempt to authenticate the user
        User user = userService.login(email, password);

        if (user != null) {
            session.setAttribute("userid", user.getId());
            if ("Admin".equals(user.getRole())) {
                return "admindashboard";

            } else if ("User".equals(user.getRole())) {
                return "redirect:/home";
            }
        }
        model.addAttribute("fail", "Incorrect username or password");
        return "redirect:/login";
    }
}



