package pl.dtit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Order.TABLE_NAME)
public class Order implements Serializable {
    public static final String TABLE_NAME = "client_order";
    private static final String COLUMN_ORDER_ID_NAME = "id_order";
    private static final String COLUMN_CLIENT_ID_NAME = "client_id";
    private static final String TABLE_ORDER_PRODUCTS_NAME = "order_products";
    private static final String COLUMN_ORDER_ID_NAME_TABLE_OP = "order_id";
    private static final String COLUMN_PRODUCT_ID_NAME_TABLE_OP = "product_id";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name=COLUMN_ORDER_ID_NAME)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = TABLE_ORDER_PRODUCTS_NAME,
            joinColumns = {@JoinColumn(name=COLUMN_ORDER_ID_NAME_TABLE_OP,
                    referencedColumnName=COLUMN_ORDER_ID_NAME)},
            inverseJoinColumns = {@JoinColumn(name=COLUMN_PRODUCT_ID_NAME_TABLE_OP,
                    referencedColumnName=Product.COLUMN_PRODUCT_ID_NAME)}
    )
    private List<Product> products = new ArrayList<>();
    private Status status;
//    @ManyToOne
//    @JoinColumn(name = COLUMN_CLIENT_ID_NAME)
//    private Client client;
    private LocalDate dateAdded;
    private double totalPrice;

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": liczba produktów - ");
        sb.append(products.size());
        sb.append(", \n");
        sb.append(products);

        return sb.toString();
    }
}
