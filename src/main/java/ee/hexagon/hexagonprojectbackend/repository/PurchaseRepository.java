package ee.hexagon.hexagonprojectbackend.repository;

import ee.hexagon.hexagonprojectbackend.model.ClientUser;
import ee.hexagon.hexagonprojectbackend.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByClientUserAndChapter(ClientUser clientUser, Long chapter);
    List<Purchase> findByClientUser(ClientUser clientUser);
}
