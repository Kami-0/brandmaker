package ru.kami.brandmaker.core.controller;

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
@RequestMapping("user")
public class UserController implements UserApi {

    @EJB
    private UserService userService;

    @Override
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @Override
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
