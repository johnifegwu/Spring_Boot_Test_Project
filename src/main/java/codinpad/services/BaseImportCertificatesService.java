package codinpad.services;

import codinpad.dto.CertificateDto;
import codinpad.entities.Certificate;
import codinpad.entities.Device;
import codinpad.exceptions.InvalidSerialNumberException;
import codinpad.repositories.CertificateRepository;
import com.google.gson.Gson;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***
 * This is the base Certificate import service class, we will extend this class for CSV Certificate import and others.
 */
@Service
public class BaseImportCertificatesService {

    private final CertificateRepository certificateRepository;
    private final Gson gson;


    public BaseImportCertificatesService(CertificateRepository certificateRepository) {
        this.gson = new Gson();
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> saveCertificates(List<CertificateDto> certificateDtos) throws IOException {
        List<Certificate> certificates = new ArrayList<>();
        // iterate through certificateDtos and then process the dtos line by line and use findOrCreateCertificate.
        // finally save the data
        for (CertificateDto certificateDto : certificateDtos) {

            //Try to find Certificate from database by hex serial number
            Certificate certificate = this.certificateRepository.findByIdAndSerialNumber(certificateDto.getId(), certificateDto.getHexSerialNumber(), certificateDto.getDecimalSerialNumber());

            if (certificate != null) {
                //Update certificate
                certificate = UpdateCertificate(certificateDto, certificate);
            }
            else{
                //Insert certificate
                certificate = InsertCertificate(certificateDto);
            }

            certificates.add(certificate);
        }

        return certificates;
    }

    private Certificate UpdateCertificate(CertificateDto certificateDto, Certificate certificate) {

        //Update Certificate status.
        certificate.setStatus(certificateDto.getStatus());
        //Handle Status
        certificate.setStatusByValidUntil();

        this.certificateRepository.save(certificate);

        return certificate;
    }

    private Certificate InsertCertificate(CertificateDto certificateDto) {
        Certificate certificate = new Certificate();
        certificate.setId(certificateDto.getId());

        //Check for hex serial number
        if(!certificateDto.getHexSerialNumber().isEmpty()) {
            certificate.setSerialNumber(certificateDto.getHexSerialNumber());
        }
        else if (!certificateDto.getDecimalSerialNumber().isEmpty()){
            certificate.setSerialNumber(certificateDto.getDecimalSerialNumber());
        }

        certificate.setSubject(certificateDto.getSubject());
        certificate.setStatus(certificateDto.getStatus());
        certificate.setValidFrom(certificateDto.getValidFrom());
        certificate.setValidUntil(certificateDto.getValidUntil());
        certificate.setIssuer(certificateDto.getIssuer());
        certificate.setCertificateIdentifier(certificateDto.getCertificateIdentifier());
        certificate.setS3Url(certificateDto.getS3Url());
        certificate.setUploadedToSftp(certificateDto.isUploadedToSftp());
        certificate.setCreatedAt(certificateDto.getCreatedAt());
        certificate.setUpdatedAt(certificateDto.getUpdatedAt());
        certificate.setRevoked(certificateDto.isRevoked());
        certificate.setCertificateFingerprint(certificateDto.getCertificateFingerprint());
        certificate.setOldCertificate(certificateDto.isOldCertificate());
        certificate.setFqdn(certificateDto.getFqdn());
        //certificate.setCertificateStatus(certificateDto.getCertificateStatus());
        certificate.setDevice(certificateDto.getDevice());

        //Handle Status
        certificate.setStatusByValidUntil();

        this.certificateRepository.save(certificate);

        return certificate;
    }

    public List<Certificate> mapCertificateCtosToCertificates(List<CertificateDto> certificateDtos) {
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateDto certificateDto : certificateDtos) {
            Certificate certificate = new Certificate();
            certificate.setId(certificateDto.getId());

            //Check for hex serial number
            if(!certificateDto.getHexSerialNumber().isEmpty()) {
                certificate.setSerialNumber(certificateDto.getHexSerialNumber());
            }
            else if (!certificateDto.getDecimalSerialNumber().isEmpty()){
                certificate.setSerialNumber(certificateDto.getDecimalSerialNumber());
            }

            certificate.setSubject(certificateDto.getSubject());
            certificate.setStatus(certificateDto.getStatus());
            certificate.setValidFrom(certificateDto.getValidFrom());
            certificate.setValidUntil(certificateDto.getValidUntil());
            certificate.setIssuer(certificateDto.getIssuer());
            certificate.setCertificateIdentifier(certificateDto.getCertificateIdentifier());
            certificate.setS3Url(certificateDto.getS3Url());
            certificate.setUploadedToSftp(certificateDto.isUploadedToSftp());
            certificate.setCreatedAt(certificateDto.getCreatedAt());
            certificate.setUpdatedAt(certificateDto.getUpdatedAt());
            certificate.setRevoked(certificateDto.isRevoked());
            certificate.setCertificateFingerprint(certificateDto.getCertificateFingerprint());
            certificate.setOldCertificate(certificateDto.isOldCertificate());
            certificate.setFqdn(certificateDto.getFqdn());
            //certificate.setCertificateStatus(certificateDto.getCertificateStatus());
            certificate.setDevice(certificateDto.getDevice());

            //Handle Status
            certificate.setStatusByValidUntil();

            certificates.add(certificate);
        }

        return certificates;
    }

    CertificateDto mapCertificateRecord(String[] record) throws InvalidSerialNumberException {
        CertificateDto dto = new CertificateDto();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        dto.setId(Long.parseLong(record[0]));

        //Check for hex serial number
        if(isHexSerialNumber(record[1])) {
            dto.setHexSerialNumber(record[0]);
        }
        else if(isDecimalSerialNumber(record[1])){
            dto.setDecimalSerialNumber(record[1]);
        }

        dto.setSubject(record[2]);
        dto.setStatus(record[3]);
        dto.setValidFrom(LocalDateTime.parse(record[4], dateTimeFormatter));
        dto.setValidUntil(LocalDateTime.parse(record[5], dateTimeFormatter));
        dto.setIssuer(record[6]);
        dto.setCertificateIdentifier(record[7]);
        dto.setS3Url(record[8]);
        dto.setUploadedToSftp(Boolean.parseBoolean(record[9]));
        dto.setCreatedAt(ZonedDateTime.parse(record[10], dateTimeFormatter));
        dto.setUpdatedAt(ZonedDateTime.parse(record[11], dateTimeFormatter));
        dto.setRevoked(Boolean.parseBoolean(record[12]));
        dto.setCertificateFingerprint(record[13]);
        dto.setOldCertificate(Boolean.parseBoolean(record[14]));
        dto.setFqdn(record[15]);
        dto.setCertificateStatus(record[16]);
        dto.setDevice(gson.fromJson(record[17], Device.class));

        return dto;
    }

    private boolean isDecimalSerialNumber(String csvRecord) throws InvalidSerialNumberException {

        if(csvRecord.startsWith("0"))
            throw new InvalidSerialNumberException();

        boolean hasDigit = false;
        boolean hasHex = false;

        for (char c : csvRecord.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            else{
                hasHex = true;
            }
        }

        if((!hasDigit && hasHex) || (hasDigit && hasHex))
            throw new InvalidSerialNumberException();

        return true;
    }

    private boolean isHexSerialNumber(String csvRecord) {
        boolean hasDigit = false;
        boolean hasHex = false;

        for (char c : csvRecord.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            else{
                hasHex = true;
            }

            if(hasDigit && hasHex)
                break;
        }

        return (hasHex && hasDigit);
    }
}
