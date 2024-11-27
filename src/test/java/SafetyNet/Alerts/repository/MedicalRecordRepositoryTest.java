package SafetyNet.Alerts.repository;

import SafetyNet.Alerts.model.MedicalRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordRepositoryTest {

    ReadData readData;
    {
        try {
            readData = new ReadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository(readData);

    @Test
    void findAllMedicalRecordsTest() {
        assertNotNull(medicalRecordRepository.findAllMedicalRecords());
    }

    @Test
    void findMedicalRecordByFirstNameAndLastNameTest() {
        MedicalRecord medic = new MedicalRecord();
        medic.setFirstName("John");
        medic.setLastName("Boyd");
        medic.setBirthdate("03/06/1984");
        medic.setAllergies(new String[] {"nillacilan"});
        medic.setMedications(new String[] {"aznol:350mg", "hydrapermazol:100mg"});
        assertEquals(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("John", "Boyd"), medic);
    }
}