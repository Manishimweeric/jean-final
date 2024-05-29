package net.mycampany.myWEBAPPStudy.service;


import net.mycampany.myWEBAPPStudy.Model.Category;
import net.mycampany.myWEBAPPStudy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category AddCategory(Category category){
        return  categoryRepository.save(category);
    }

    public List<Category> AllCategories(){
        List<Category> listdata=categoryRepository.findAll();
        return listdata;
    }

}
