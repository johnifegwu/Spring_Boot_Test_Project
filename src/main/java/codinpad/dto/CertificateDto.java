package codinpad.dto;

import codinpad.entities.Device;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class CertificateDto {
    private Long id;
    private String decimalSerialNumber;
    private String hexSerialNumber;
    private String subject;
    private String status;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private String issuer;
    private String certificateIdentifier;
    private String s3Url;
    private boolean isUploadedToSftp=false;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean isRevoked=false;
    private String certificateFingerprint;
    private boolean isOldCertificate = false;
    private String fqdn;
    private String certificateStatus;
    private ZonedDateTime certificateStatusUpdatedAt;
    private Device device;
    private boolean isInstalledCertificate = false;
    private boolean isFqdnMisMatch = false;
}