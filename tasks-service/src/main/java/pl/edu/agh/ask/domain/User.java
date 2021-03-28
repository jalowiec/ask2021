package pl.edu.agh.ask.domain;


import javax.persistence.*;

@Entity(name = "users")
public class User {

    public User() {
    }

    public User(Long id, String email, int keyClockId) {
        this.id = id;
        this.email = email;
        this.keyClockId = keyClockId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private int keyClockId;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getKeyClockId() {
        return keyClockId;
    }
}
