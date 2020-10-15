package ru.kami.brandmaker.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kami.brandmaker.core.api.dto.UserDto;
import ru.kami.brandmaker.core.domain.UserEntity;
import ru.kami.brandmaker.core.repository.UserRepository;
import ru.kami.brandmaker.core.utils.DtoToEntityConverter;
import ru.kami.brandmaker.core.utils.EntityToDtoConverter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

/**
 * @author Daniil.Makarov
 */
@TransactionAttribute(NOT_SUPPORTED)
@Stateless
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        final UserEntity userEntity = userRepository
                .save(EntityToDtoConverter.convert(userDto));
        return DtoToEntityConverter.convert(userEntity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(DtoToEntityConverter::convert)
                .collect(Collectors.toList());
    }

    public void deleteUser(long id) {
        userRepository.delete(userRepository.getOne(id));
    }
}
