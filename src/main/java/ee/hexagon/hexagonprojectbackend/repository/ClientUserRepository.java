package ee.hexagon.hexagonprojectbackend.repository;

import ee.hexagon.hexagonprojectbackend.model.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    Optional<ClientUser> findClientUserByEmailIgnoreCase(String email);
}
