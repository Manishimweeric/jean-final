package net.mycampany.myWEBAPPStudy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String product_id;
    private  String product_name;
    private Long quantity;
    private Float UnitPrice;
    @Transient
    private MultipartFile imagefile;
    private String image ;
    private String Description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cart> cat_products;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")
    private Set<OrderProduct> orderProducts = new HashSet<>();


    public Product() {
    }

    public Product(Integer id, String product_id, String product_name, Long quantity, Float unitPrice, MultipartFile imagefile, String image, String description, Category category, List<Cart> cat_products, Set<OrderProduct> orderProducts) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
        UnitPrice = unitPrice;
        this.imagefile = imagefile;
        this.image = image;
        Description = description;
        this.category = category;
        this.cat_products = cat_products;
        this.orderProducts = orderProducts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        UnitPrice = unitPrice;
    }

    public MultipartFile getImagefile() {
        return imagefile;
    }

    public void setImagefile(MultipartFile imagefile) {
        this.imagefile = imagefile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Cart> getCat_products() {
        return cat_products;
    }

    public void setCat_products(List<Cart> cat_products) {
        this.cat_products = cat_products;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
