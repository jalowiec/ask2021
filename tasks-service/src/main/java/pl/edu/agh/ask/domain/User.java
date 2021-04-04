package pl.edu.agh.ask.domain;


import javax.persistence.*;

@Entity(name = "users")
public class User {

    public User() {
    }

    public User(Long id, String email, String keyCloakUserName) {
        this.id = id;
        this.email = email;
        this.keyCloakUserName = keyCloakUserName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String keyCloakUserName;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getKeyCloakUserName() {
        return keyCloakUserName;
    }
}
