package pl.codecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

public class UploadController {

    @Autowired
    private UploadServiceImpl uploadService;

    @Autowired
    private MessageSource messageSource;

    private String fileLocation;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getUploadPage(){
        return "upload";
    }

    @RequestMapping(value = "/uploadExcelFile", method = RequestMethod.POST)
    public String uploadFile(Model model, MultipartFile file) throws IOException {

        uploadService.uploadFile(fileLocation, file);
/*
        InputStream input = file.getInputStream();
        File currentDir = new File(".");
        String path = currentDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = input.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();*/

        model.addAttribute("message", "File: " + file.getOriginalFilename() + " został załadowany pomyślnie!");
        return "upload";
    }

    @RequestMapping(value = "/validateExcelFile", method = RequestMethod.GET)
    public String readPOI(Model model) throws IOException {

        if (fileLocation != null) {
            if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
                Map<Integer, List<ExcelCell>> data = uploadService.uploadExcelFile(fileLocation);
                model.addAttribute("data", data);
            } else {
                String wrongFileError = messageSource.getMessage("wrong.file.format", new String[]{"Wrong file error"}, Locale.getDefault());
                model.addAttribute("message", wrongFileError);
            }
        } else {
            String noFileError = messageSource.getMessage("missing.upload.file", new String[]{"No file error"}, Locale.getDefault());
            model.addAttribute("message", noFileError);
        }
        return "upload";
    }
}
