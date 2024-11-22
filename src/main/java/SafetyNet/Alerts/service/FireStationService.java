package SafetyNet.Alerts.service;

import SafetyNet.Alerts.model.FireStation;
import SafetyNet.Alerts.model.MedicalRecord;
import SafetyNet.Alerts.model.Person;
import SafetyNet.Alerts.repository.FireStationRepository;
import SafetyNet.Alerts.repository.MedicalRecordRepository;
import SafetyNet.Alerts.repository.PersonRepository;
import SafetyNet.Alerts.service.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PersonService personService;

    public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository, PersonService personService) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.personService = personService;
    }

    public List<FireStation> findAllFireStationsByNumber(Integer number) {
        return this.fireStationRepository.findAllFireStations().stream().filter(f -> f.getStation().equals(number.toString())).collect(Collectors.toList());
    }

    // Get a list of phone numbers by firestation number
    public List<String> findPhoneNumbersByFireStationNumber(Integer number) {
        List<String> result = new ArrayList<>();
        List<FireStation> fireStationList = findAllFireStationsByNumber(number);
        List<Person> personList = personRepository.findAllPersons();
        for(FireStation fireStation : fireStationList){
            List<Person> personSelect = (personList.stream().filter(f -> f.getAddress().equals(fireStation.getAddress())).toList());
            result.add(String.valueOf(personSelect.stream().map(Person::getPhone).collect(Collectors.toList())));
        }
        return result;
    }

    // Get a list of person and number of child and adult by firestation number
    public FireStationDTO findFireStationDTOByFireStationNumber(Integer number) {
        FireStationDTO result = new FireStationDTO();
        List<PersonDTO> personDTOS = new ArrayList<>();

        int adult = 0;
        int child = 0;

        List<FireStation> fireStationList = findAllFireStationsByNumber(number);
        List<Person> personList = personRepository.findAllPersons();

        for (FireStation firstation : fireStationList) {

            for (Person person : personList) {
                if (firstation.getAddress().equals(person.getAddress())) {
                    PersonDTO personSelect = new PersonDTO();
                    MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                    if (personService.getAge(medicalRecord.getBirthdate()) > 18) {
                        adult++;
                    } else {
                        child++;
                    }
                    ;
                    personSelect.setFirstName(person.getFirstName());
                    personSelect.setLastName(person.getLastName());
                    personSelect.setAddress(person.getAddress());
                    personSelect.setPhone(person.getPhone());
                    personDTOS.add(personSelect);
                }
            }
        }

                result.setPersonDTOS(personDTOS);
                result.setNumberOfAdult(adult);
                result.setNumberOfChild(child);
                return result;
    }

    // Get a list person and medicalRecord by fireStation
    public List<FireDTO> findPersonAndMedicalRecordByFireStationAddress (String address) {
        List<FireDTO> result = new ArrayList<>();
        int firstationNumber = Integer.parseInt(fireStationRepository.findAllFireStations().stream().filter(f -> f.getAddress().equals(address)).findFirst().get().getStation());
        List<Person> personList = personRepository.findAllPersonsByAdress(address);
        for(Person person :personList){
            FireDTO personSelect = new FireDTO();
            MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            personSelect.setFirstName(person.getFirstName());
            personSelect.setLastName(person.getLastName());
            personSelect.setPhone(person.getPhone());
            personSelect.setAge(personService.getAge(medicalRecord.getBirthdate()));
            personSelect.setMedications(medicalRecord.getMedications());
            personSelect.setAllergies(medicalRecord.getAllergies());
            personSelect.setFireStationNumber(firstationNumber);
            result.add(personSelect);
        }
        return result;
    }

    // Get a list of family by a list of fireStation
    public List<FloodDTO> findfamilyByFireStationList (List<String> stations) {
        List<FloodDTO> result = new ArrayList<>();
        for (String station : stations){
            FloodDTO fireStation = new FloodDTO();
            List<FamilyDTO> familyList = new ArrayList<>();
            List<FireStation> stationList = fireStationRepository.findAllFireStationByFireStationNumber(station);
                for(FireStation stationSelect : stationList){
                    List<Person> personList = personRepository
                            .findAllPersonsByAdress(stationSelect.getAddress());
                    for(Person person : personList){
                    FamilyDTO family = new FamilyDTO();
                    MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                    family.setFirstName(person.getFirstName());
                    family.setLastName(person.getLastName());
                    family.setPhone(person.getPhone());
                    family.setAllergies(medicalRecord.getAllergies());
                    family.setMedications(medicalRecord.getMedications());
                    familyList.add(family);
                    }
                }
            fireStation.setFireStation(station);
            fireStation.setFamilyList(familyList);
            result.add(fireStation);
        }
        return result;
    }
}
