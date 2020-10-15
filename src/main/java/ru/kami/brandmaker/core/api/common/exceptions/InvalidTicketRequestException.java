package ru.kami.brandmaker.core.api.common.exceptions;

import lombok.Getter;

/**
 * @author Daniil.Makarov
 */
@Getter
public class InvalidTicketRequestException extends RuntimeException {
    private final String message;

    public InvalidTicketRequestException(long placeId) {
        this.message = "Место с id: " + placeId + " уже забронировано";
    }

    public InvalidTicketRequestException(long placeId, String message) {
        this.message = "Место с id: " + placeId + message;
    }
}
