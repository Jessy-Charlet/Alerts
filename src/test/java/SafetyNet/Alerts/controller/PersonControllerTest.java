package SafetyNet.Alerts.controller;

import SafetyNet.Alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonController personController;

    @Test
    void emailsListTest() {
        assertNotNull(personController.EmailsList("Culver"));
    }

    @Test
    void childAlertDTOList() {
        assertNotNull(personController.childAlertDTOList("1509 Culver St"));
    }

    @Test
    void personInfoList() {
        assertNotNull(personController.personInfoList("John", "Boyd"));
    }
}