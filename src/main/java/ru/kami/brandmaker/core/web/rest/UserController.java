package ru.kami.brandmaker.core.web.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kami.brandmaker.core.api.UserApi;
import ru.kami.brandmaker.core.api.dto.UserDto;
import ru.kami.brandmaker.core.service.UserService;

import javax.ejb.EJB;
import java.util.List;

/**
 * @author Daniil.Makarov
 */
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController implements UserApi {

    @EJB
    private UserService userService;

    @Override
    @GetMapping(value = "")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @PutMapping(value = "/{id}/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @Override
    @DeleteMapping(value = "/{id}/delete")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
