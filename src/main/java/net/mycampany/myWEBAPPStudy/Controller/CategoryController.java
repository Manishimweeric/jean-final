package net.mycampany.myWEBAPPStudy.Controller;

import net.mycampany.myWEBAPPStudy.Model.Category;
import net.mycampany.myWEBAPPStudy.Model.User;
import net.mycampany.myWEBAPPStudy.service.CategoryService;
import net.mycampany.myWEBAPPStudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired private CategoryService categoryService;


    //to register a  User Account

    @GetMapping("/category")
    public  String showuserform(Model model){
        return "category-form";
    }

    @PostMapping("/category/save")
    public  String saveuser(Category category, Model model){
        categoryService.AddCategory(category);
        model.addAttribute("message1","Category Added Success");
        return "category-form";
    }


    @GetMapping("/display")
    public  String showuserlist(Model model){
        List<Category> listcategory=categoryService.AllCategories();
        model.addAttribute("listallCategory",listcategory);
        return "pr";
    }



    ////comment data




//    @GetMapping("/user/edit/{id}")
//    public  String showeditform( @PathVariable("id") Integer id , Model model,RedirectAttributes ra){
//        try {
//          User user=userService.get(id);
//          model.addAttribute("user",user);
//            model.addAttribute("pageTittle","UPDATE USER DETAIL" );
//            return "user_form";
//        } catch (UserNotFoundException e) {
//            ra.addFlashAttribute("message",e.getMessage());
//            return "redirect:/user";
//        }
//    }
//    @GetMapping("/user/delete/{id}")
//    public  String deleteuser( @PathVariable("id") Integer id ,RedirectAttributes ra){
//        try {
//            userService.delete(id);
//            ra.addFlashAttribute ("message","The User ID : " + id + " Has been deleted " );
//        }catch (UserNotFoundException e) {
//            ra.addFlashAttribute("message",e.getMessage());
//
//        }
//        return "redirect:/user";
//    }

}
