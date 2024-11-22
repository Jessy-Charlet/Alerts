package SafetyNet.Alerts.repository;

import SafetyNet.Alerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {

    private final ReadData readData;

    public MedicalRecordRepository(ReadData readData) {
        this.readData = readData;
    }

    public List<MedicalRecord> findAllMedicalRecords() {
        return readData.getData().getMedicalrecords();
    }

    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
        return readData.getData().getMedicalrecords().stream()
                .filter(f -> f.getLastName().equals(lastName))
                .filter(f -> f.getFirstName().equals(firstName))
                .findFirst().get();
    }

}
