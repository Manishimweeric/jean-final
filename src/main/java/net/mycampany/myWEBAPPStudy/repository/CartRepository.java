package net.mycampany.myWEBAPPStudy.repository;

import net.mycampany.myWEBAPPStudy.Model.Cart;
import net.mycampany.myWEBAPPStudy.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query("select a from Cart a where a.status = 'Pending'")
    List<Cart> carts_pending();
}
