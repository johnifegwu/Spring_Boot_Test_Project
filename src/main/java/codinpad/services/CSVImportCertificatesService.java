package codinpad.services;

import codinpad.exceptions.InvalidSerialNumberException;
import codinpad.repositories.CertificateRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import codinpad.dto.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVImportCertificatesService extends BaseImportCertificatesService {

    @Autowired
    public CSVImportCertificatesService(CertificateRepository certificateRepository) {
        super(certificateRepository);
    }


  //We will modify the parse function, so it can map to the right columns in the database.
    // We will also rename the bellow method from parse to parseCSv as this method is soley for parsing CSV file,
  // we will be adding other classes for parsing XLS or XML, hence we will rename this class from ImportCertificatesService to CSVImportCertificatesService
  public List<CertificateDto> parseCSV(MultipartFile file) throws IOException {
    try {
        List<CertificateDto> certificateDtos = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] csvRecord;
            boolean isFirstLine = true;

            while ((csvRecord = csvReader.readNext()) != null) {

                //We will not be reading the header values
                if(isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                certificateDtos.add(mapCertificateRecord(csvRecord));
            }
        } catch (InvalidSerialNumberException e) {
            throw new IOException("CSV validation failed. " + e.getMessage(), e);
        }

        return certificateDtos;
    } catch (CsvValidationException e) {
        throw new IOException("CSV validation failed. " + e.getMessage(), e);
    }
}



}
