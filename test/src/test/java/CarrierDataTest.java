import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrierDataTest {

    @Test
    public void testAddData() {
        CarrierData carrierData = new CarrierData();

        carrierData.addData(2, 10);
        assertEquals(2, carrierData.getOnTime());
        assertEquals(10, carrierData.getLateMinutes());

        carrierData.addData(3, 15);
        assertEquals(5, carrierData.getOnTime());
        assertEquals(25, carrierData.getLateMinutes());
    }
}