package net.mycampany.myWEBAPPStudy.Model;


import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cat_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private  Integer quantity;
    private  float total;
    private  String status="Pending";


    public Cart() {
    }

    public Cart(Integer cat_id, Product product, Integer quantity, float total, String status) {
        this.cat_id = cat_id;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
