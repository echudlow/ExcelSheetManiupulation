import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Airlines {
    public static void main(String[] args) throws IOException {
        // Open the excel sheet
        FileInputStream file = new FileInputStream("src/main/resources/04_Apr_2023_Chronic_Delay.xlsx");

        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Initialize a map to house the carrierData
        Map<String, CarrierData> carrierDataMap = new HashMap<>();

        // Define the columns
        int carrierColumn = 1;
        int onTimeColumn = 8;
        int lateMinutesColumn = 10;

        // Skip the header row
        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            // Extract the cell data
            String carrier = row.getCell(carrierColumn).getStringCellValue();

            // Make sure to skip null/empty slots
            if (carrier == null || carrier.isEmpty()) {
                continue;
            }

            int onTime = (int) row.getCell(onTimeColumn).getNumericCellValue();
            int lateMinutes = (int) row.getCell(lateMinutesColumn).getNumericCellValue();

            // Add the data together
            CarrierData data = carrierDataMap.get(carrier);
            if (data == null) {
                data = new CarrierData();
                carrierDataMap.put(carrier, data);
            }
            data.addData(onTime, lateMinutes);

        }

        // Close the streamer
        file.close();

        // Create a new Excel sheet
        Workbook outWorkbook = new XSSFWorkbook();
        Sheet outSheet = outWorkbook.createSheet("Summary");

        // Write the header row
        Row headerRow = outSheet.createRow(0);
        headerRow.createCell(0).setCellValue("MARKETING CARRIER");
        headerRow.createCell(1).setCellValue("NUMBER OF FLIGHT OPERATIONS NOT ARRIVING ON TIME");
        headerRow.createCell(2).setCellValue("NUMBER OF MINUTES LATE");

        // Write the data rows
        int rowIndex = 1;
        for (Map.Entry<String, CarrierData> entry : carrierDataMap.entrySet()) {
            Row dataRow = outSheet.createRow(rowIndex++);
            dataRow.createCell(0).setCellValue(entry.getKey());
            dataRow.createCell(1).setCellValue(entry.getValue().getOnTime());
            dataRow.createCell(2).setCellValue(entry.getValue().getLateMinutes());
        }

        // Save the new Excel Sheet
        FileOutputStream fos = new FileOutputStream("output.xlsx");
        outWorkbook.write(fos);
        fos.close();
    }
}

