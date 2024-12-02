package nxc.hcmus.gscore.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvService {
    void importDataFromCsv(String filePath);
}
