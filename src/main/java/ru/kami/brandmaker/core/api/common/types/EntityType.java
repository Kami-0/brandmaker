package ru.kami.brandmaker.core.api.common.types;

/**
 * @author Daniil.Makarov
 */
public enum EntityType {
    CINEMA("Кинотеатр"),
    PLACE("Кинотеатр"),
    EVENT("Событие");

    private final String name;

    EntityType(String name) {
        this.name = name;
    }
}
