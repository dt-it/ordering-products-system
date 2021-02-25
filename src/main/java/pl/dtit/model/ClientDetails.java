package pl.dtit.model;

import javax.persistence.*;

@Entity
@Table(name = ClientDetails.TABLE_NAME)
public class ClientDetails {
    public static final String TABLE_NAME = "client_details";
    private static final String COLUMN_CLIENT_DETAILS_ID_NAME = "id_client_details";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_CLIENT_DETAILS_ID_NAME)
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    private String address;
    @OneToOne(mappedBy = "details")
    private Client client;

    public ClientDetails() {}

    public ClientDetails(String firstname, String lastname, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstname;
    }
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }
    public String getLastName() {
        return lastname;
    }
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
