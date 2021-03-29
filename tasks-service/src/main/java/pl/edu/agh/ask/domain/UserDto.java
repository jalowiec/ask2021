package pl.edu.agh.ask.domain;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String email;
    private int keyCloakId;

    public UserDto() {
    }

    public UserDto(Long id, String email,  int keyCloakId) {
        this.id = id;
        this.email = email;
        this.keyCloakId = keyCloakId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (keyCloakId != userDto.keyCloakId) return false;
        if (!Objects.equals(id, userDto.id)) return false;
        return Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + keyCloakId;
        return result;
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

    public int getKeyCloakId() {
        return keyCloakId;
    }

    public void setKeyCloakId(int keyCloakId) {
        this.keyCloakId = keyCloakId;
    }
}
