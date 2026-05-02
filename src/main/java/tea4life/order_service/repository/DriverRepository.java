package tea4life.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tea4life.order_service.model.driver.Driver;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByKeycloakId(String keycloakId);
}
