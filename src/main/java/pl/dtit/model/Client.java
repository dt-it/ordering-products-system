package pl.dtit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Client.TABLE_NAME)
public class Client implements Serializable {
    public static final String TABLE_NAME = "client";
    private static final String COLUMN_CLIENT_ID_NAME = "id_client";
    private static final String COLUMN_CLIENT_DETAILS_NAME = "id_details";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_CLIENT_ID_NAME)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @OneToOne
    @JoinColumn(name = COLUMN_CLIENT_DETAILS_NAME)
    private ClientDetails details;
//    @OneToMany(mappedBy = "client")
//    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public Client(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientDetails getDetails() {
        return details;
    }

    public void setDetails(ClientDetails details) {
        this.details = details;
    }

    //    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ");
        sb.append(username);
        sb.append(" ");
        sb.append(email);
        sb.append(" (password: ");
        sb.append(password);
        sb.append(")");
        return sb.toString();
    }
}
