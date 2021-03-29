package pl.edu.agh.ask.domain;


import javax.persistence.*;

@Entity(name = "users")
public class User {

    public User() {
    }

    public User(Long id, String email, int keyCloakId) {
        this.id = id;
        this.email = email;
        this.keyCloakId = keyCloakId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private int keyCloakId;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getKeyCloakId() {
        return keyCloakId;
    }
}
