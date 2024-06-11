package codinpad.services;

import codinpad.dto.CertificateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import codinpad.entities.Certificate;
import codinpad.entities.Device;
import codinpad.entities.CertificateBuilder;
import codinpad.entities.DeviceBuilder;
import codinpad.repositories.CertificateRepository;
import codinpad.repositories.DeviceRepository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class CertificateImportServiceIntegrationTest {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private CSVImportCertificatesService importCertificatesService;

    @Test
    public void testImportCertificatesFromFile() throws Exception {
        Map<String, Certificate> existingCertificatesMap = new HashMap<>();
        Map<String, Device> devicesMap = new HashMap<>();

        // Existing certificates where status won't change
        Certificate existingValidCertificate = new CertificateBuilder().withSerialNumber("72057594037927936").build();
        existingCertificatesMap.put("existingValidCertificate", existingValidCertificate);

        Certificate existingRevokedCertificate = new CertificateBuilder().withSerialNumber("72057594037927938").build();
        existingCertificatesMap.put("existingRevokedCertificate", existingRevokedCertificate);

        Certificate existingExpiredCertificate = new CertificateBuilder().withSerialNumber("72057594037927945").build();
        existingCertificatesMap.put("existingExpiredCertificate", existingExpiredCertificate);


        // Existing certificates where status will be changed
        Certificate existingValidCertificateRevokedInCsv = new CertificateBuilder().withSerialNumber("72057594037927947").build();
        existingCertificatesMap.put("existingValidCertificateRevokedInCsv", existingValidCertificateRevokedInCsv);

        Certificate existingValidCertificateExpiredInCsv = new CertificateBuilder().withSerialNumber("72057594037927948").build();
        existingCertificatesMap.put("existingValidCertificateExpiredInCsv", existingValidCertificateExpiredInCsv);

        Certificate existingValidCertificateRevokedAndExpiredByDateInCsv = new CertificateBuilder().withSerialNumber("72057594037927950").build();
        existingCertificatesMap.put("existingValidCertificateRevokedAndExpiredByDateInCsv", existingValidCertificateRevokedAndExpiredByDateInCsv);

        Certificate existingValidCertificateValidButExpiredByDateInCsv = new CertificateBuilder().withSerialNumber("72057594037927952").build();
        existingCertificatesMap.put("existingValidCertificateValidButExpiredByDateInCsv", existingValidCertificateValidButExpiredByDateInCsv);

        // Existing certificates related to devices
        Device deviceForExistingValidCertificateAlreadyAssignedToDevice = new DeviceBuilder().withFqdn("test2035022.device.company.com").build();
        devicesMap.put("deviceForExistingValidCertificateAlreadyAssignedToDevice", deviceForExistingValidCertificateAlreadyAssignedToDevice);
        deviceRepository.save(deviceForExistingValidCertificateAlreadyAssignedToDevice);

        Certificate existingValidCertificateAlreadyAssignedToDevice = new CertificateBuilder()
                .withSerialNumber("72057594037927954")
                .withDevice(deviceForExistingValidCertificateAlreadyAssignedToDevice)
                .build();
        existingCertificatesMap.put("existingValidCertificateAlreadyAssignedToDevice", existingValidCertificateAlreadyAssignedToDevice);

        Device deviceForExistingValidCertificateNotAssignedToDevice = new DeviceBuilder().withFqdn("test0154022.device.company.com").build();
        devicesMap.put("deviceForExistingValidCertificateNotAssignedToDevice", deviceForExistingValidCertificateNotAssignedToDevice);
        deviceRepository.save(deviceForExistingValidCertificateNotAssignedToDevice);

        Certificate existingValidCertificateNotAssignedToDevice = new CertificateBuilder().withSerialNumber("72057594037927940").build();
        existingCertificatesMap.put("existingValidCertificateNotAssignedToDevice", existingValidCertificateNotAssignedToDevice);


        // Existing certificates where serial number will be changed
        Certificate existingValidCertificateWithHexSerialNumber = new CertificateBuilder().withSerialNumber("10000000000001a").build();
        existingCertificatesMap.put("existingValidCertificateWithHexSerialNumber", existingValidCertificateWithHexSerialNumber);

        Certificate existingValidCertificateWithHexAndLeadingZeroSerialNumber = new CertificateBuilder().withSerialNumber("010000000000001b").build();
        existingCertificatesMap.put("existingValidCertificateWithHexAndLeadingZeroSerialNumber", existingValidCertificateWithHexAndLeadingZeroSerialNumber);

        certificateRepository.saveAll(existingCertificatesMap.values());


        ClassPathResource classPathResource = new ClassPathResource("test-certificates.csv");

        // Use a temporary file to copy the content of the resource
        Path tempFile = Files.createTempFile("test-cert", ".csv");
        try (InputStream inputStream = classPathResource.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        byte[] content = Files.readAllBytes(tempFile);
        MultipartFile file = new MockMultipartFile("file", "test-certificates.csv", "text/csv", content);
    }
}