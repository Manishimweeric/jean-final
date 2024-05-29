package net.mycampany.myWEBAPPStudy.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Order_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    private Float total_amount;
    private String Status="Pending";


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private Set<OrderProduct> orderProducts = new HashSet<>();


    public Order(Integer order_id, Date orderDate, Float total_amount, String status, Set<OrderProduct> orderProducts) {
        Order_id = order_id;
        this.orderDate = orderDate;
        this.total_amount = total_amount;
        Status = status;
        this.orderProducts = orderProducts;
    }



    public Order() {
    }

    public Integer getId() {
        return Order_id;
    }

    public void setId(Integer id) {
        this.Order_id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Float total_amount) {
        this.total_amount = total_amount;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public Integer getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(Integer order_id) {
        Order_id = order_id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
