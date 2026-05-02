package tea4life.order_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tea4life.order_service.dto.request.driver.UpsertDriverRequest;
import tea4life.order_service.dto.response.driver.DriverResponse;
import tea4life.order_service.model.driver.Driver;
import tea4life.order_service.repository.DriverRepository;
import tea4life.order_service.service.DriverService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class DriverServiceImpl implements DriverService {

    // Repository
    DriverRepository driverRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponse> findAllDrivers() {
        return driverRepository.findAll().stream()
                .map(this::toDriverResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DriverResponse findDriverById(Long id) {
        return toDriverResponse(findDriverEntityById(id));
    }

    @Override
    public DriverResponse createDriver(UpsertDriverRequest request) {
        Driver driver = new Driver();
        applyRequestToDriver(driver, request);

        try {
            return toDriverResponse(driverRepository.save(driver));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "keycloakId của driver đã tồn tại", ex);
        }
    }

    @Override
    public DriverResponse updateDriver(Long id, UpsertDriverRequest request) {
        Driver driver = findDriverEntityById(id);
        applyRequestToDriver(driver, request);

        try {
            return toDriverResponse(driverRepository.save(driver));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "keycloakId của driver đã tồn tại", ex);
        }
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.delete(findDriverEntityById(id));
    }

    // =================================================
    // Lookup
    // =================================================

    private Driver findDriverEntityById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy Driver với ID: " + id));
    }

    // =================================================
    // Mapping
    // =================================================

    private void applyRequestToDriver(Driver driver, UpsertDriverRequest request) {
        driver.setKeycloakId(request.keycloakId().trim());
        driver.setFullName(request.fullName().trim());
        driver.setPhone(request.phone().trim());
    }

    private DriverResponse toDriverResponse(Driver driver) {
        return new DriverResponse(
                driver.getId() == null ? null : driver.getId().toString(),
                driver.getKeycloakId(),
                driver.getFullName(),
                driver.getPhone()
        );
    }
}
