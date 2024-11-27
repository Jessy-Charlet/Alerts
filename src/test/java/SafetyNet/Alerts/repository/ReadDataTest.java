package SafetyNet.Alerts.repository;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadDataTest {

    ReadData readData;
    {
        try {
            readData = new ReadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getDataTest() {
        assertNotNull( readData.getData());
    }

}