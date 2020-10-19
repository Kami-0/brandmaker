package ru.kami.brandmaker.core.api.dto;

import lombok.*;

/**
 * @author Daniil.Makarov
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@EqualsAndHashCode
public class UserDto {
    private Long userId;
    private String name;
    private String surname;
    private String email;
    private Long yearOfBirth;
}
