package ru.kami.brandmaker.core.api;

import ru.kami.brandmaker.core.api.constant.ApiConstants;
import ru.kami.brandmaker.core.api.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author Daniil.Makarov
 */
public interface UserApi {
    /**
     * Получить список всех пользователей
     *
     * @return список пользователей
     */
    List<UserDto> getAllUsers();

    /**
     * Создать пользователя
     *
     * @param userDto сущность пользователя
     * @return пользователь
     */
    UserDto createUser(@Valid UserDto userDto);

    /**
     * Удалить пользователя
     *
     * @param id пользователя
     */
    void deleteUser(@Min(value = ApiConstants.ID_MIN, message = ApiConstants.MESSAGE_TO_NOT_VALID_ID) long id);
}
