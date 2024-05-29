package net.mycampany.myWEBAPPStudy.repository;

import net.mycampany.myWEBAPPStudy.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("select a from Order a where a.Status = 'pending'")
    List<Order> order_pending();
}
