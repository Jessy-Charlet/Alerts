package SafetyNet.Alerts.repository;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FireStationRepositoryTest {

    ReadData readData;
    {
        try {
            readData = new ReadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    FireStationRepository fireStationRepository = new FireStationRepository(readData);

    @Test
    void findAllFireStationByFireStationNumberTest() {
        assertNotNull(fireStationRepository.findAllFireStationByFireStationNumber("1"));
    }

    @Test
    void findAllFireStationsTest() {
        assertNotNull(fireStationRepository.findAllFireStations());
    }
}