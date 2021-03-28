package pl.edu.agh.ask.domain;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String email;
    private int keyClockId;

    public UserDto() {
    }

    public UserDto(Long id, String email,  int keyClockId) {
        this.id = id;
        this.email = email;
        this.keyClockId = keyClockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (keyClockId != userDto.keyClockId) return false;
        if (!Objects.equals(id, userDto.id)) return false;
        return Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + keyClockId;
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

    public int getKeyClockId() {
        return keyClockId;
    }

    public void setKeyClockId(int keyClockId) {
        this.keyClockId = keyClockId;
    }
}
