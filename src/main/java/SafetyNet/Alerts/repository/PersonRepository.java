package SafetyNet.Alerts.repository;

import SafetyNet.Alerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    private final ReadData readData;

    public PersonRepository(ReadData readData) {
        this.readData = readData;
    }

    public List<Person> findAllPersons() {
        return readData.getData().getPersons();
    }

    public List<Person> findAllPersonsByAdress(String address) {
        return readData.getData().getPersons().stream().filter(f -> f.getAddress().equals(address)).toList();
    }

    public List<Person> findAllPersonByName(String firstName, String lastName) {
        return readData.getData().getPersons().stream()
                .filter(f -> f.getLastName().equals(lastName))
                .filter(f -> f.getFirstName().equals(firstName)).toList();
    }

}
