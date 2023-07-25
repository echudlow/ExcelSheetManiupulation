import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.IOException;
import java.util.Map;

public class AirlineChartCreator {

    public static void main(String[] args) throws IOException {
        new AirlineChartCreator().createCharts();

    }
    public void createCharts() throws IOException {

        // Instantiate LateFlights
        LateFlights lateFlights = new LateFlights();

        // Instantiate DepartureTimes
        DepartureTimes departureTimes = new DepartureTimes();

        // Create a map for the flightTimes
        Map<String, Integer> totalMinutesLateByAirline = lateFlights.getTotalMinutesLateByAirline();
        // Create a map for the departures
        Map<String, Map<String, Integer>> departuresBeforeAndAfterNoonByAirline = departureTimes.getDeparturesBeforeAndAfterNoonByAirline();


        DefaultCategoryDataset minutesLateDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : totalMinutesLateByAirline.entrySet()) {
            minutesLateDataset.addValue(entry.getValue(), "Total Minutes Late", entry.getKey());
        }

        // Create the total minutes late chart
        JFreeChart minutesLateChart = ChartFactory.createBarChart(
                "Total Minutes Late by Airline",
                "Airline",
                "Total Minutes Late",
                minutesLateDataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        ChartFrame minutesLateFrame = new ChartFrame("Total Minutes Late By Airline", minutesLateChart);
        minutesLateFrame.pack();
        minutesLateFrame.setVisible(true);

        DefaultCategoryDataset departuresDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Map<String, Integer>> entry : departuresBeforeAndAfterNoonByAirline.entrySet()) {
            Map<String, Integer> departures = entry.getValue();
            departuresDataset.addValue(departures.get("Before Noon"), "Before Noon", entry.getKey());
            departuresDataset.addValue(departures.get("After Noon"), "After Noon", entry.getKey());
        }

        // Create the departure times chart
        JFreeChart departuresChart = ChartFactory.createBarChart(
                "Departures Before and After Noon by Airline",
                "Airline",
                "Number of Departures",
                departuresDataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartFrame departuresFrame = new ChartFrame("Departures Before and After Noon by Airline", departuresChart);
        departuresFrame.pack();
        departuresFrame.setVisible(true);

    }

}


