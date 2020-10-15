package ru.kami.brandmaker.core.api.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static ru.kami.brandmaker.core.api.constant.ApiConstants.ID_MIN;
import static ru.kami.brandmaker.core.api.constant.ApiConstants.MESSAGE_TO_NOT_VALID_ID;

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
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String email;
    @NotNull
    private Long yearOfBirth;
}
