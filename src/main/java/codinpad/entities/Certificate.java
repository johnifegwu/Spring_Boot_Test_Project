package codinpad.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import codinpad.enums.CertificateStatusEnum;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "certificates")
public class Certificate implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  // In the DB in this field sometimes we have 0abbacf1234 sometimes abbacf1234 and sometimes 232395438475936;
  @Column(name = "serial_number")
  private String serialNumber;

  @Column(name = "subject")
  private String subject;

  @Column(name = "status")
  private String status;

  @Column(name = "valid_from")
  private LocalDateTime validFrom;

  @Column(name = "valid_until")
  private LocalDateTime validUntil;

  @Column(name = "issuer")
  private String issuer;

  @Column(name = "certificate_identifier")
  private String certificateIdentifier;

  @Column(name = "s3_url")
  private String s3Url;

  @Column(name = "is_uploaded_to_sftp")
  private boolean isUploadedToSftp=false;

  @Column(name = "created_at")
  @CreatedDate
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  @LastModifiedDate
  private ZonedDateTime updatedAt;

  @Column(name = "is_revoked")
  private boolean isRevoked=false;

  @Column(name = "certificate_fingerprint")
  private String certificateFingerprint;

  @Column(name = "is_old_certificate")
  private boolean isOldCertificate = false;

  @Column(name = "fqdn")
  private String fqdn;

  @Column(name = "certificate_status")
  private String certificateStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id")
  @JsonIgnore
  private Device device;

  public boolean isExpired() {
      if (validUntil == null) {
          return true;
      }

      return LocalDateTime.now().isAfter(validUntil);
  }

  private String getCertificateStatus() {
    return certificateStatus;
  }

  private LocalDateTime getValidUntil() {
    return validUntil;
  }

  void setCertificateStatus(String newCertificateStatus) {
    this.certificateStatus = newCertificateStatus;
  }

  public Certificate setStatusByValidUntil() {
    // Check if the certificate status is already revoked
    if (CertificateStatusEnum.REVOKED.certificateStatus.equalsIgnoreCase(this.getCertificateStatus())) {
      // Do not change the status if it is revoked
      return this;
    }

    // If the certificate is not revoked, proceed to check its validity
    if (this.getValidUntil() != null) {
      if (LocalDateTime.now().isAfter(this.getValidUntil())) {
        this.setCertificateStatus(CertificateStatusEnum.EXPIRED.certificateStatus);
        //replacing certificate (line: 122, col: 130) with this as there isn't any variable of such.
      } else if ((LocalDateTime.now().plusDays(44).equals(this.getValidUntil()) || LocalDateTime.now().plusDays(44).isAfter(this.getValidUntil()))) {
      } else if (LocalDateTime.now().plusDays(44).isAfter(this.getValidUntil())) {
        // If the certificate is within 44 days of expiring, mark it as nearing expiry
        this.setCertificateStatus(CertificateStatusEnum.NEARING_EXPIRY.certificateStatus);
      } else {
        // Otherwise, if the certificate is still valid, mark it as valid
        this.setCertificateStatus(CertificateStatusEnum.VALID.certificateStatus);
      }
    }

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Certificate that = (Certificate) o;
    return isUploadedToSftp == that.isUploadedToSftp && isRevoked == that.isRevoked && isOldCertificate == that.isOldCertificate && Objects.equals(id, that.id) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(subject, that.subject) && Objects.equals(status, that.status) && Objects.equals(validFrom, that.validFrom) && Objects.equals(validUntil, that.validUntil) && Objects.equals(issuer, that.issuer) && Objects.equals(certificateIdentifier, that.certificateIdentifier) && Objects.equals(s3Url, that.s3Url) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(certificateFingerprint, that.certificateFingerprint) && Objects.equals(fqdn, that.fqdn) && Objects.equals(certificateStatus, that.certificateStatus) && Objects.equals(device, that.device);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, serialNumber, subject, status, validFrom, validUntil, issuer, certificateIdentifier, s3Url, isUploadedToSftp, createdAt, updatedAt, isRevoked, certificateFingerprint, isOldCertificate, fqdn, certificateStatus, device);
  }

  @Override
  public String toString() {
    return "Certificate{" +
            "id=" + id +
            ", serialNumber='" + serialNumber + '\'' +
            ", subject='" + subject + '\'' +
            ", status='" + status + '\'' +
            ", validFrom=" + validFrom +
            ", validUntil=" + validUntil +
            ", issuer='" + issuer + '\'' +
            ", certificateIdentifier='" + certificateIdentifier + '\'' +
            ", s3Url='" + s3Url + '\'' +
            ", isUploadedToSftp=" + isUploadedToSftp +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", isRevoked=" + isRevoked +
            ", certificateFingerprint='" + certificateFingerprint + '\'' +
            ", isOldCertificate=" + isOldCertificate +
            ", fqdn='" + fqdn + '\'' +
            ", certificateStatus='" + certificateStatus + '\'' +
            ", device=" + device +
            '}';
  }
}
