package codinpad.controllers;

import java.util.*;

import codinpad.dto.CertificateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import codinpad.entities.Certificate;
import codinpad.entities.Device;
import codinpad.repositories.CertificateRepository;
import codinpad.services.CSVImportCertificatesService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@ComponentScan(basePackages = {"codinpad", "org.springframework.boot.autoconfigure.orm.jpa","codinpad.config", "codinpad.controllers",
        "codinpad.dto", "codinpad.entities", "codinpad.enums", "codinpad.exceptions", "codinpad.repositories", "codinpad.services"})
@RequestMapping("/")
public class CertificateController {

    @Autowired
    private CSVImportCertificatesService csvImportCertificatesService;

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping("/directory")
    public String getDirectory(Model model) {
        model.addAttribute("certificates", certificateRepository.findAll());
        return "directory";
    }

    // get all certificates
    @GetMapping("/certificates")
    @ResponseBody
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    // create certificate rest api
    @PostMapping("/certificates")
    @ResponseBody
    public Certificate createCertificate(@RequestBody Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    // get certificate by id rest api
    @GetMapping("/certificates/{id}")
    @ResponseBody
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isPresent()) {
            return ResponseEntity.ok(optionalCertificate.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete certificate rest api
    @DeleteMapping("/certificates/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> deleteCertificate(@PathVariable Long id) {
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        if (!optionalCertificate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Certificate certificate = optionalCertificate.get();
        certificateRepository.delete(certificate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // We will modify the code here to handle different kinds of file types which include CSV and others.
    @PostMapping("/certificates/upload")
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();

        try {
            // check file type
            String ext = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(file.getOriginalFilename()
                            .lastIndexOf('.') + 1);
            if (ext.equalsIgnoreCase("csv")) {
                // Parse CSV
                List<CertificateDto> dtos = csvImportCertificatesService.parseCSV(file);
                // Save data
                csvImportCertificatesService.saveCertificates(dtos);
            } else if (ext.equalsIgnoreCase("xls")) {
                // TODO: Handle XLS implementation here.

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("XLS file up;oad not implemented, File upload not successful, File: " + fileName + "!");
            }

            return ResponseEntity.ok("File uploaded successfully: " + fileUri);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + fileName + "!");
        }
    }
}