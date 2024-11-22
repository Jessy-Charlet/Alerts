package SafetyNet.Alerts.repository;

import SafetyNet.Alerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepository {

    private final ReadData readData;

    public FireStationRepository(ReadData readData) {
        this.readData = readData;
    }

    public List<FireStation> findAllFireStations() {
        return readData.getData().getFirestations();
    }

    public List<FireStation> findAllFireStationByFireStationNumber(String number) {
        return readData.getData().getFirestations().stream().filter(f -> f.getStation().equals(number)).toList();
    }
}
