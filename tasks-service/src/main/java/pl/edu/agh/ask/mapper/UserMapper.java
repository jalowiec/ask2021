package pl.edu.agh.ask.mapper;


import org.springframework.stereotype.Component;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.domain.TaskDto;
import pl.edu.agh.ask.domain.User;
import pl.edu.agh.ask.domain.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return new User(
             userDto.getId(),
             userDto.getEmail(),
             userDto.getKeyClockId()
        );
    }

    public UserDto mapToTaskDto(final User user){
        return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getKeyClockId()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(n->new UserDto(n.getId(), n.getEmail(), n.getKeyClockId()))
                .collect(Collectors.toList());
    }

}