package codinpad.services;

import codinpad.dto.CertificateDto;
import codinpad.repositories.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class XSLImportCertificatesService extends BaseImportCertificatesService {

    @Autowired
    public XSLImportCertificatesService(CertificateRepository certificateRepository) {
        super(certificateRepository);
    }

    public List<CertificateDto> parseXLS(MultipartFile file) throws IOException {
        List<CertificateDto> certificateDtos = new ArrayList<>();

        //TODO: Implement logic to parse XLS file here

        return certificateDtos;
    }
}
