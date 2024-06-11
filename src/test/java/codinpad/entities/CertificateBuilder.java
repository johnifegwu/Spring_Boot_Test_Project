package codinpad.entities;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import codinpad.entities.Certificate;
import codinpad.entities.Device;

public class CertificateBuilder {
    private final Certificate certificate;

    public CertificateBuilder() {
        certificate = new Certificate();
        certificate.setSerialNumber("863753242536989727");
        certificate.setSubject("CN=m0121022.device.baintern.de,OU=Bulk CA,OU=PKI,O=Some Company,C=DE");
        certificate.setCertificateStatus("valid");
        certificate.setValidFrom(LocalDate.now().atStartOfDay());
        certificate.setValidUntil(LocalDate.now().atStartOfDay().plus(1, ChronoUnit.YEARS));
        certificate.setIssuer("CN=BA-Bulk-CA-10:PN,O=Some Company,C=DE");
        certificate.setCertificateIdentifier("");
        certificate.setCertificateFingerprint("EB:AB:AB:AB:AB:BA:BA:BA:BA:AB:CD:AB:CD:AB:CD:DC:BA:DC:BA:A5");
        certificate.setRevoked(false);
        certificate.setCreatedAt(ZonedDateTime.now());
        // Initialize default values if needed
    }

    public CertificateBuilder withSerialNumber(String serialNumber) {
        certificate.setSerialNumber(serialNumber);
        return this;
    }

    public CertificateBuilder withCertificateStatus(String certificateStatus) {
        certificate.setCertificateStatus(certificateStatus);
        return this;
    }

    public CertificateBuilder withDevice(Device device) {
        certificate.setDevice(device);
        return this;
    }

    public Certificate build() {
        return certificate;
    }
}