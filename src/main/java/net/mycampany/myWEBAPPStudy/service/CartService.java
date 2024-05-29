package net.mycampany.myWEBAPPStudy.service;

import net.mycampany.myWEBAPPStudy.Model.Cart;
import net.mycampany.myWEBAPPStudy.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart registercart(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> allcats(){
       return cartRepository.carts_pending();
    }

    public Cart deleteItem(Integer cat_id) {
        Optional<Cart> optionalCats = cartRepository.findById(cat_id);
        if (optionalCats.isPresent()) {
            Cart cats = optionalCats.get();
            cartRepository.delete(cats);
            return cats;
        } else {
            throw new RuntimeException("Cat not found with id " + cat_id);
        }
    }

    public List<Cart> updateCartItems(List<Cart> catIds) {
        // Initialize a list to store the updated cart items
        List<Cart> updatedCats = new ArrayList<>();

        // Iterate over the list of cart item IDs
        for (Cart catId : catIds) {
            // Find the cart item by ID
            Optional<Cart> optionalCats = cartRepository.findById(catId.getCat_id());
            if (optionalCats.isPresent()) {
                Cart cats = optionalCats.get();
                // Update the status of the cart item to "Ordered"
                cats.setStatus("Ordered");
                // Save the updated cart item
                updatedCats.add(cartRepository.save(cats));
            } else {
                throw new RuntimeException("Cart item not found with ID: " + catId);
            }
        }

        // Return the list of updated cart items
        return updatedCats;
    }

}
