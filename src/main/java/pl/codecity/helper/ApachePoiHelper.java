package pl.codecity.helper;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ApachePoiHelper {

    /**
     * This is the main method of class.
     * @param fileLocation
     * @return data
     * @throws IOException
     */
    public Map<Integer, List<ExcelCell>> readExcel(String fileLocation) throws IOException {

        Map<Integer, List<ExcelCell>> data = new HashMap<>();
        FileInputStream fis = new FileInputStream(new File(fileLocation));

        if (fileLocation.endsWith(".xls")) {
            data = readHSSFWorkbook(fis);
        } else if (fileLocation.endsWith(".xlsx")) {
            data = readXSSFWorkbook(fis);
        }

        int maxNrCols = data.values().stream().mapToInt(List::size).max().orElse(0);

        data.values().stream().filter(ls -> ls.size() < maxNrCols).forEach(ls -> {
            IntStream.range(ls.size(), maxNrCols).forEach(i -> ls.add(new ExcelCell("")));
        });
        return data;
    }

    /**
     * This method reads each one cell of excel document and validates
     * types of data
     * @param cell
     * @return content
     */
    private String readCellContent(Cell cell) {
        String content;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                content = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    content = cell.getDateCellValue() + "";
                } else {
                    content = cell.getNumericCellValue() + "";
                }
                break;
            case BOOLEAN:
                content = cell.getBooleanCellValue() + "";
                break;
            case FORMULA:
                content = cell.getCellFormula() + "";
                break;
            default:
                content = "";
        }
        return content;
    }

    /**
     * This method parsing files terminated xls.
     * @param fis
     * @return data
     * @throws IOException
     */
    private Map<Integer, List<ExcelCell>> readHSSFWorkbook(FileInputStream fis) throws IOException {
        Map<Integer, List<ExcelCell>> data = new HashMap<>();
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(fis);

            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                data.put(i, new ArrayList<>());
                if (row != null) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        HSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            HSSFCellStyle cellStyle = cell.getCellStyle();

                            ExcelCell excelCell = new ExcelCell();

                            HSSFColor bgColor = cellStyle.getFillForegroundColorColor();
                            if (bgColor != null) {
                                short[] rgbColor = bgColor.getTriplet();
                                excelCell.setBgColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
                            }
                            HSSFFont font = cell.getCellStyle()
                                    .getFont(workbook);
                            excelCell.setTextSize(font.getFontHeightInPoints() + "");
                            if (font.getBold()) {
                                excelCell.setTextWeight("bold");
                            }
                            HSSFColor textColor = font.getHSSFColor(workbook);
                            if (textColor != null) {
                                short[] rgbColor = textColor.getTriplet();
                                excelCell.setTextColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
                            }
                            excelCell.setContent(readCellContent(cell));
                            data.get(i)
                                    .add(excelCell);
                        } else {
                            data.get(i)
                                    .add(new ExcelCell(""));
                        }
                    }
                }
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return data;
    }

    /**
     * This method parsing files terminated xlsx.
     * @param fis
     * @return data
     * @throws IOException
     */
    private Map<Integer, List<ExcelCell>> readXSSFWorkbook(FileInputStream fis) throws IOException {
        XSSFWorkbook workbook = null;
        Map<Integer, List<ExcelCell>> data = new HashMap<>();
        try {

            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                data.put(i, new ArrayList<>());
                if (row != null) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            XSSFCellStyle cellStyle = cell.getCellStyle();

                            ExcelCell excelCell = new ExcelCell();
                            XSSFColor bgColor = cellStyle.getFillForegroundColorColor();
                            if (bgColor != null) {
                                byte[] rgbColor = bgColor.getRGB();
                                excelCell.setBgColor("rgb(" + (rgbColor[0] < 0 ? (rgbColor[0] + 0xff) : rgbColor[0]) + "," + (rgbColor[1] < 0 ? (rgbColor[1] + 0xff) : rgbColor[1]) + "," + (rgbColor[2] < 0 ? (rgbColor[2] + 0xff) : rgbColor[2]) + ")");
                            }
                            XSSFFont font = cellStyle.getFont();
                            excelCell.setTextSize(font.getFontHeightInPoints() + "");
                            if (font.getBold()) {
                                excelCell.setTextWeight("bold");
                            }
                            XSSFColor textColor = font.getXSSFColor();
                            if (textColor != null) {
                                byte[] rgbColor = textColor.getRGB();
                                excelCell.setTextColor("rgb(" + (rgbColor[0] < 0 ? (rgbColor[0] + 0xff) : rgbColor[0]) + "," + (rgbColor[1] < 0 ? (rgbColor[1] + 0xff) : rgbColor[1]) + "," + (rgbColor[2] < 0 ? (rgbColor[2] + 0xff) : rgbColor[2]) + ")");
                            }
                            excelCell.setContent(readCellContent(cell));
                            data.get(i)
                                    .add(excelCell);
                        } else {
                            data.get(i)
                                    .add(new ExcelCell(""));
                        }
                    }
                }
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return data;
    }
}
