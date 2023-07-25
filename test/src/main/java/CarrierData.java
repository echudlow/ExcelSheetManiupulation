
// Created so that I could use as an object in airlines
public class CarrierData {
    private int onTime;
    private int lateMinutes;

    void addData(int onTime, int lateMinutes) {
        this.onTime += onTime;
        this.lateMinutes += lateMinutes;
    }

    int getOnTime() {
        return onTime;
    }

    int getLateMinutes() {
        return lateMinutes;
    }
}