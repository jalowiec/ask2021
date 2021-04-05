package pl.edu.agh.ask.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.ask.domain.TaskDto;
import pl.edu.agh.ask.domain.User;
import pl.edu.agh.ask.domain.UserDto;
import pl.edu.agh.ask.mapper.TaskMapper;
import pl.edu.agh.ask.mapper.UserMapper;
import pl.edu.agh.ask.service.DbService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {


    @Autowired
    private DbService service;

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Optional<User> getUserByUserId(Long userId){
        return service.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUser(@AuthenticationPrincipal Jwt jwt) throws UserNotFoundException {
        String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
        return userMapper.mapToUserDtoList(service.getUserByKeyClocUserName(keyCloakUserName));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto, @AuthenticationPrincipal Jwt jwt) throws UserAlreadyExistException {
        String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
        if(service.getUserByKeyClocUserName(keyCloakUserName).size() > 0) throw new UserAlreadyExistException();
        else {
            userDto.setKeyCloakUserName(keyCloakUserName);
            service.saveUser(userMapper.mapToUser(userDto));
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public TaskDto updateUser(@RequestBody UserDto userDto, @AuthenticationPrincipal Jwt jwt) throws TaskNotFoundException {
        String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
        Optional<User> user = Optional.ofNullable(service.getUserByKeyClocUserName(keyCloakUserName).get(0));
        if(user.isPresent()){
            userDto.setId(user.get().getId());
            userDto.setKeyCloakUserName(keyCloakUserName);
            service.saveUser(userMapper.mapToUser(userDto));
        }
        return null;
    }

}
