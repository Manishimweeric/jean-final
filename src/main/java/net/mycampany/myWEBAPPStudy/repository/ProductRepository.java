package net.mycampany.myWEBAPPStudy.repository;

import net.mycampany.myWEBAPPStudy.Model.Order;
import net.mycampany.myWEBAPPStudy.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT usd from Product usd where usd.id = :p_id")
    Product getproduct(@Param("p_id") Integer p_id);

    @Query("SELECT p FROM Product p WHERE p.id = :p_id")
    Product findProductById(@Param("p_id") Integer id);

}
