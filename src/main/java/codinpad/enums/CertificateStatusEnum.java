package codinpad.enums;

public enum CertificateStatusEnum {
    VALID("valid"),
    NEARING_EXPIRY("nearing-expiry"),
    EXPIRED("expired"),
    REVOKED("revoked");

    public final String certificateStatus;

    CertificateStatusEnum(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }
}