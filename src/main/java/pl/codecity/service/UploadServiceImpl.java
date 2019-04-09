package pl.codecity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.codecity.helper.ApachePoiHelper;
import pl.codecity.helper.UploadHelper;
import pl.codecity.model.ExcelCell;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UploadServiceImpl {

    @Autowired
    private ApachePoiHelper apachePoiHelper;

    @Autowired
    private UploadHelper uploadHelper;

    public Map<Integer, List<ExcelCell>> uploadExcelFile(String fileLocation) throws IOException {
        return apachePoiHelper.readExcel(fileLocation);
    }

    public void uploadFile(String fileLocation, MultipartFile file) throws IOException {
        uploadHelper.uploadFile(fileLocation, file);
    }
}
