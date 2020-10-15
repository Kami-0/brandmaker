package ru.kami.brandmaker.core.utils;

import ru.kami.brandmaker.core.api.dto.UserDto;
import ru.kami.brandmaker.core.domain.UserEntity;

/**
 * @author Daniil.Makarov
 */
public final class DtoToEntityConverter {

    public static UserDto convert(UserEntity userEntity) {
        return new UserDto(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getEmail(),
                userEntity.getYearOfBirth()
        );
    }

}
