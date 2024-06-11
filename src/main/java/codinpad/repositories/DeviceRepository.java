package codinpad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codinpad.entities.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query("SELECT d FROM Device d WHERE (d.internalId = ?1)")
    Device findDeviceByInternalId(String deviceId);
}
