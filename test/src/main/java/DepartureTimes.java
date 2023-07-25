import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DepartureTimes {

    public Map<String, Map<String, Integer>> getDeparturesBeforeAndAfterNoonByAirline() throws IOException {
        // Read in Chronic_Delay
        Workbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/04_Apr_2023_Chronic_Delay.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);

        // Indices for the columns
        int carrierColumn = 1;
        int departureTimeColumn = 6;

        Map<String, Map<String, Integer>> departuresBeforeAndAfterNoonByAirline = new HashMap<>();

        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String carrier = row.getCell(carrierColumn).getStringCellValue();

            int time = (int)row.getCell(departureTimeColumn).getNumericCellValue();
            int departureHour = time / 100; // convert military time to hours

            String timePeriod;
            if (departureHour < 12) {
                timePeriod = "Before Noon";
            } else {
                timePeriod = "After Noon";
            }

            Map<String, Integer> departuresByTimePeriod = departuresBeforeAndAfterNoonByAirline.getOrDefault(carrier, new HashMap<>());
            departuresByTimePeriod.put(timePeriod, departuresByTimePeriod.getOrDefault(timePeriod, 0) + 1);

            departuresBeforeAndAfterNoonByAirline.put(carrier, departuresByTimePeriod);
        }

        workbook.close();
        // This was added as a check for NullPointerExceptions
        System.out.println("Departures before and after noon by airline: " + departuresBeforeAndAfterNoonByAirline);
        return departuresBeforeAndAfterNoonByAirline;
    }
}
