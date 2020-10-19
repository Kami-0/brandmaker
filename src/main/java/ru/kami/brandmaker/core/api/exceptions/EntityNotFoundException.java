package ru.kami.brandmaker.core.api.exceptions;

import lombok.Getter;

/**
 * @author Daniil.Makarov
 */
@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String message;

    public EntityNotFoundException(long id) {
        this.message = "Сущность с id: " + id + " не существует";
    }
}
