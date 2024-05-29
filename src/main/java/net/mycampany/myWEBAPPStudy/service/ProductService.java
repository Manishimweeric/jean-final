package net.mycampany.myWEBAPPStudy.service;


import net.mycampany.myWEBAPPStudy.Model.Product;
import net.mycampany.myWEBAPPStudy.repository.CategoryRepository;
import net.mycampany.myWEBAPPStudy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    public Product AddProduct(Product product) {
       return productRepository.save(product);
    }

    public List<Product> Allproduct(){
        return productRepository.findAll();
    }

    public  Product getiid(Integer Pid){
        return productRepository.findProductById(Pid);
    }
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Integer id) {
      return productRepository.getproduct(id);
    }

    public void updateProduct(Integer id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setProduct_name(product.getProduct_name());
            updatedProduct.setQuantity(product.getQuantity());
            updatedProduct.setUnitPrice(product.getUnitPrice());
            updatedProduct.setDescription(product.getDescription());
            productRepository.save(updatedProduct);
        }
    }
}
