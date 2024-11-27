package SafetyNet.Alerts.controller;

import SafetyNet.Alerts.repository.FireStationRepository;
import SafetyNet.Alerts.repository.PersonRepository;
import SafetyNet.Alerts.service.FireStationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FireStationControllerTest {

    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    FireStationController fireStationController;

    @Test
    void findPhoneNumberByFireStationNumberTest() {
        assertNotNull(fireStationController.findPhoneNumberByFireStationNumber(1));
    }

    @Test
    void findPersonsByFireStationNumberTest() {
        assertNotNull(fireStationController.findPersonsByFireStationNumber(1));
    }

    @Test
    void findPersonAndMedicalRecordByAddressTest() {
        assertNotNull(fireStationController.findPersonAndMedicalRecordByAddress("1509 Culver St"));
    }

    @Test
    void findFamilyByFireStationListTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        assertNotNull(fireStationController.findFamilyByFireStationList(list));
    }
}