package codinpad.services;

import codinpad.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import codinpad.dto.CertificateDto;
import codinpad.dto.CertificateDtoBuilder;
import codinpad.repositories.CertificateRepository;

import java.util.Arrays;
import java.util.List;

public class CertificateImportServiceTest {
    @Mock
    private CertificateRepository certificateRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private CSVImportCertificatesService importCertificatesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private List<CertificateDto> mockParsedCertificateDtos() {
        CertificateDto dto1 = new CertificateDtoBuilder()
                .withHexSerialNumber("bfcaaff6b02541f")
                .withDecimalSerialNumber("863753242536989727")
                .withSubject("CN=test0121022.device.company.com,OU=Bulk CA,OU=PKI,O=Some Company,C=DE")
                .withCertificateStatus("ausgestellt")
                .build();

        CertificateDto dto2 = new CertificateDtoBuilder()
                .withHexSerialNumber("bf8e5ff74e03368")
                .withDecimalSerialNumber("862692213981721448")
                .withSubject("CN=test0121022.device.company.com,OU=Bulk CA,OU=PKI,O=Some Company,C=DE")
                .withCertificateStatus("gesperrt")
                .build();

        CertificateDto dto3 = new CertificateDtoBuilder()
                .withHexSerialNumber("80f69ff8d3ea388")
                .withDecimalSerialNumber("580799423261352840")
                .withSubject("CN=test0154022.device.company.com,OU=Bulk CA,OU=PKI,O=Some Company,C=DE")
                .withCertificateStatus("gesperrt")
                .build();

        CertificateDto dto4 = new CertificateDtoBuilder()
                .withHexSerialNumber("e8023ff5169c288")
                .withDecimalSerialNumber("1044874693039473288")
                .withSubject("CN=test0404022.device.company.com,OU=Bulk CA,OU=PKI,O=Some Company,C=DE")
                .withCertificateStatus("ausgestellt")
                .build();

        return Arrays.asList(dto1, dto2, dto3, dto4);
    }
}