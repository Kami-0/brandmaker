package ru.kami.brandmaker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kami.brandmaker.core.domain.UserEntity;

/**
 * @author Daniil.Makarov
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}