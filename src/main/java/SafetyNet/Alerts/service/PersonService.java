package SafetyNet.Alerts.service;

import SafetyNet.Alerts.model.MedicalRecord;
import SafetyNet.Alerts.model.Person;
import SafetyNet.Alerts.repository.MedicalRecordRepository;
import SafetyNet.Alerts.repository.PersonRepository;
import SafetyNet.Alerts.service.dto.ChildAlertDTO;
import SafetyNet.Alerts.service.dto.PersonInfoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public PersonService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<String> findAllCityByEmail(String city) {
        return this.personRepository.findAllPersons().stream().filter(p -> p.getCity().equals(city)).map(Person::getEmail).collect(Collectors.toList());
    }

    // get child and family
    public List<ChildAlertDTO> findAllChildByAddress(String address) {
        List<ChildAlertDTO> result = new ArrayList<>();
        List<Person> personList = personRepository.findAllPersonsByAdress(address);
        for (Person person : personList) {
            List<Person> family = new ArrayList<>();
            MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            ChildAlertDTO child = new ChildAlertDTO();
            if (medicalRecord.getAge(medicalRecord.getBirthdate()) < 18) {
                child.setFirstName(person.getFirstName());
                child.setLastname(person.getLastName());
                child.setAge(medicalRecord.getAge(medicalRecord.getBirthdate()));
                for (Person adult : personList) {
                    if (!adult.getFirstName().equals(person.getFirstName())) {
                        family.add(person);
                    }
                }
                child.setFamily(family);
                result.add(child);
            }
        }
        return result;
    }

    // get perso info by name
    public List<PersonInfoDTO> findAllInfoByName(String firstName, String lastName) {
        List<PersonInfoDTO> result = new ArrayList<>();
        List<Person> personList = personRepository.findAllPersonByName(lastName, firstName);
        for (Person person :personList) {
            MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            PersonInfoDTO persoInfo = new PersonInfoDTO();
            persoInfo.setFirstName(person.getFirstName());
            persoInfo.setLastName(person.getLastName());
            persoInfo.setAddress(person.getAddress());
            persoInfo.setEMail(person.getEmail());
            persoInfo.setMedications(medicalRecord.getMedications());
            persoInfo.setAllergies(medicalRecord.getAllergies());
            persoInfo.setAge(medicalRecord.getAge(medicalRecord.getBirthdate()));
            result.add(persoInfo);
        }
        return result;
    }
}

