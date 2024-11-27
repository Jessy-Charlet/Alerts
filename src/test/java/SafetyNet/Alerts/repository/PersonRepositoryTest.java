package SafetyNet.Alerts.repository;

import SafetyNet.Alerts.model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    ReadData readData;
    {
        try {
            readData = new ReadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    PersonRepository personRepository = new PersonRepository(readData);

    @Test
    void findAllPersonsTest() {
        assertNotNull(personRepository.findAllPersons());
    }

    @Test
    void findAllPersonsByAdressTest() {
       /* Person personTest = new Person();
        personTest.setFirstName("John");
        personTest.setLastName("Boyd");
        personTest.setCity("Culver");
        personTest.setPhone("841-874-6512");
        personTest.setAddress("1509 Culver St");
        personTest.setZip("97451");
        personTest.setEmail("jaboyd@email.com");*/
        assertNotNull(personRepository.findAllPersonsByAdress("1509 Culver St"));

    }

    @Test
    void findAllPersonByNameTest() {
        assertNotNull(personRepository.findAllPersonByName("John", "Boyd"));
    }
}