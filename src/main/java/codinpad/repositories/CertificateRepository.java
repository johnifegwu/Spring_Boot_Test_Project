package codinpad.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codinpad.entities.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
   @Query("SELECT c FROM Certificate c WHERE ((c.id = ?1 ) AND (c.serialNumber = ?2 OR c.serialNumber = ?3))")
   Certificate findByIdAndSerialNumber(long Id, String SerialNumber, String DecimalSerialNumber);
}
