package ru.kami.brandmaker.core.utils;

import ru.kami.brandmaker.core.api.dto.UserDto;
import ru.kami.brandmaker.core.domain.UserEntity;

/**
 * @author Daniil.Makarov
 */
public final class EntityToDtoConverter {
    public static UserEntity convert(UserDto userDto) {
        return new UserEntity(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getYearOfBirth()
        );
    }
}
