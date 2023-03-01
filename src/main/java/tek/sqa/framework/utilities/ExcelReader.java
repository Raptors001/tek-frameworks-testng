package tek.sqa.framework.utilities;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static tek.sqa.framework.utilities.FileUtilities.getFileInputSteam;

public class ExcelReader {

    public static Sheet getExcelSheet(String filePath, int sheetIndex) throws IOException {
       // ExtentTestManager.getTest().info("Reading Excel Sheet in location " + filePath);
        FileInputStream fileInputSteam = getFileInputSteam(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputSteam);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        if (sheet == null)
            throw new RuntimeException("Requested Sheet is not Available");
        return sheet;
    }

    public static List<Map<String, Object>> readSheetWithFirstRowAsHeader(Sheet sheet) {
        List<Map<String, Object>> list = new LinkedList<>();
        for (int row = 0; row <= sheet.getLastRowNum(); row++) {
            if (row != 0) {
                Map<String, Object> map = new HashMap<>();
                Row firstRow = getRow(sheet, 0);
                for (int col = 0; col < firstRow.getLastCellNum(); col++) {
                    String key = firstRow.getCell(col).getStringCellValue();
                    Object value = getCellValue(getCell(sheet, row, col));
                    //Object value = getCellValue(getCell(sheet, 6, 2)); c6
                    map.put(key, value);
                }
                list.add(map);
            }
        }
        return list;
    }

    public static List<List<Object>> readSheetWithoutHeader(Sheet sheet) {
        List<List<Object>> list = new LinkedList<>();
        for (int row = 0; row <= sheet.getLastRowNum(); row++) {
            if (row != 0) {
                List<Object> rowData = new LinkedList<>();
                Row firstRow = getRow(sheet, 0);
                for (int col = 0; col < firstRow.getLastCellNum(); col++) {

                    rowData.add(getCellValue(getCell(sheet, row, col)));

                }
                list.add(rowData);
            }
        }
        return list;
    }

    public static List<Object> readSheetAllInOneList(Sheet sheet) {
        List<Object> list = new LinkedList<>();
        for (int row = 0; row <= sheet.getLastRowNum(); row++) {
            if (row != 0) {
                Row firstRow = getRow(sheet, 0);
                for (int col = 0; col < firstRow.getLastCellNum(); col++) {
                    list.add(getCellValue(getCell(sheet, row, col)));
                }
            }
        }
        return list;
    }


    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            default:
                return null;
        }
    }

    private static Cell getCell(Sheet sheet, int row, int col) {
        return sheet.getRow(row).getCell(col);
    }

    private static Row getRow(Sheet sheet, int row) {
        return sheet.getRow(row);
    }


  //  getCell(getExcelSheet,6,2);
}
