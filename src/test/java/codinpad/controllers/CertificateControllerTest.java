package codinpad.controllers;

import codinpad.dto.CertificateDto;
import codinpad.entities.Certificate;
import codinpad.entities.CertificateBuilder;
import codinpad.repositories.CertificateRepository;
import codinpad.services.CSVImportCertificatesService;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@WebMvcTest(controllers = CertificateController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CertificateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CSVImportCertificatesService realCSVImportCertificatesService;

    @MockBean
    private CSVImportCertificatesService mockCSVImportCertificatesService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("classpath:certificates.csv")
    Resource certificatesCSV;

    private Certificate certificate;

    private Gson gson;

    private MockMultipartFile file;

    @BeforeEach
    public void init() throws IOException {
        gson = new Gson();
        certificate = new CertificateBuilder().build();
        //Get certificates from resources
        byte[] certArray = new byte[0];
        try {
            certArray = getSavedCertificateFromTestResource();
        }
        catch (Exception e) {
            throw new IOException("CSV Resource Read Error " + e.getMessage(), e);
        }
        file = new MockMultipartFile("file", "certificate.csv", "text/csv", certArray);
    }


    @Test
    public void mockTestUploadCertificate() throws Exception {
        // check file type
        String ext = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename()
                        .lastIndexOf('.') + 1);
        if (ext.equalsIgnoreCase("csv")) {
            // Parse CSV
            List<CertificateDto> dtos = mockCSVImportCertificatesService.parseCSV(file);
            List<Certificate> certificates = mockCSVImportCertificatesService.mapCertificateCtosToCertificates(dtos);
            // Save data
            when(mockCSVImportCertificatesService.saveCertificates(dtos)).thenReturn(certificates);

            MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(MockMvcRequestBuilders.multipart("/certificates/upload")
                            .file(file))
                    .andExpect(status().is(200))
                    .andExpect(content().string("File uploaded successfully: http://localhost/uploads/certificate.csv"));

        } else if (ext.equalsIgnoreCase("xls")) {
            // TODO: Handle XLS implementation here.
        }
    }

    @Test
    public void realTestUploadCertificate() throws Exception {
        // check file type
        String ext = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename()
                        .lastIndexOf('.') + 1);
        if (ext.equalsIgnoreCase("csv")) {
            // Parse CSV
            List<CertificateDto> dtos = realCSVImportCertificatesService.parseCSV(file);
            List<Certificate> certificates = realCSVImportCertificatesService.mapCertificateCtosToCertificates(dtos);
            // Save data
            realCSVImportCertificatesService.saveCertificates(dtos);

            MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(MockMvcRequestBuilders.multipart("/certificates/upload")
                    .file(file))
                    .andExpect(status().is(200))
                    .andExpect(content().string("File uploaded successfully: http://localhost/uploads/certificate.csv"));

        } else if (ext.equalsIgnoreCase("xls")) {
            // TODO: Handle XLS implementation here.
        }
    }

    private byte[] getSavedCertificateFromTestResource() throws IOException {
        // Creating an object of FileInputStream to 
        // read from a file 
        FileInputStream fl = new FileInputStream(certificatesCSV.getFile());

        // Now creating byte array of same length as file 
        byte[] arr = new byte[(int)certificatesCSV.getFile().length()];

        // Reading file content to byte array 
        // using standard read() method 
        fl.read(arr);

        // lastly closing an instance of file input stream 
        // to avoid memory leakage 
        fl.close();

        // Returning above byte array 
        return arr;
    }
}
