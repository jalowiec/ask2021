package pl.edu.agh.ask.domain;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String email;
    private String keyCloakUserName;

    public UserDto() {
    }

    public UserDto(Long id, String email,  String keyCloakUserName) {
        this.id = id;
        this.email = email;
        this.keyCloakUserName = keyCloakUserName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeyCloakUserName() {
        return keyCloakUserName;
    }

    public void setKeyCloakUserName(String keyCloakUserName) {
        this.keyCloakUserName = keyCloakUserName;
    }
}
