package codinpad.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CertificateDtoBuilder {
    private CertificateDto dto;

    public CertificateDtoBuilder() {
        dto = new CertificateDto();
        dto.setHexSerialNumber("bfcaaff6b02541f");
        dto.setDecimalSerialNumber("863753242536989727");
        dto.setSubject("CN=m0121022.device.baintern.de,OU=Bulk CA,OU=PKI,O=Some Company,C=DE");
        dto.setCertificateStatus("ausgestellt");
        dto.setValidFrom(LocalDate.now().atStartOfDay());
        dto.setValidUntil(LocalDate.now().atStartOfDay().plus(1, ChronoUnit.YEARS));
        dto.setIssuer("CN=BA-Bulk-CA-10:PN,O=Some Company,C=DE");
        dto.setCertificateIdentifier("");
        dto.setCertificateFingerprint("EB:AB:AB:AB:AB:BA:BA:BA:BA:AB:CD:AB:CD:AB:CD:DC:BA:DC:BA:A5");
        dto.setRevoked(false);
    }

    public CertificateDtoBuilder withHexSerialNumber(String hexSerialNumber) {
        dto.setHexSerialNumber(hexSerialNumber);
        return this;
    }

    public CertificateDtoBuilder withDecimalSerialNumber(String decimalSerialNumber) {
        dto.setDecimalSerialNumber(decimalSerialNumber);
        return this;
    }

    public CertificateDtoBuilder withSubject(String subject) {
        dto.setSubject(subject);
        return this;
    }

    public CertificateDtoBuilder withCertificateStatus(String status) {
        dto.setCertificateStatus(status);
        return this;
    }

    public CertificateDtoBuilder withFingerprint(String fingerprint) {
        dto.setCertificateFingerprint(fingerprint);
        return this;
    }

    public CertificateDto build() {
        return dto;
    }
}