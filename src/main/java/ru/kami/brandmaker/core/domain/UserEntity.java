package ru.kami.brandmaker.core.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author Daniil.Makarov
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "year_of_birth")
    private Long yearOfBirth;
}
