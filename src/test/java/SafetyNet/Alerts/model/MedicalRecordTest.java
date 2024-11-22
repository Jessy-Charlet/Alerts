package SafetyNet.Alerts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordTest {

    MedicalRecord medicalRecord = new MedicalRecord();

    @Test
    void getAgeTest() {
        assertEquals( medicalRecord.getAge("04/13/1989"), 35);
    }
}