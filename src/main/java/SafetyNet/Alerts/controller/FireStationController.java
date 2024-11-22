package SafetyNet.Alerts.controller;

import SafetyNet.Alerts.model.Person;
import SafetyNet.Alerts.service.FireStationService;
import SafetyNet.Alerts.service.dto.FireDTO;
import SafetyNet.Alerts.service.dto.FireStationDTO;
import SafetyNet.Alerts.service.dto.FloodDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {

    private final FireStationService firestationservice;

    public FireStationController(FireStationService firestationservice) {
        this.firestationservice = firestationservice;
    }

    // Get phone numbers by firestations
    @GetMapping(value ="phoneAlert")
    public List<String> findPhoneNumberByFireStationNumber(@RequestParam(name="fireStation") Integer number){
        return this.firestationservice.findPhoneNumbersByFireStationNumber(number);
    }

    // Get person by firestations
    @GetMapping(value="firestation")
    public FireStationDTO findPersonsByFireStationNumber(@RequestParam(name="stationNumber") Integer number){
        return this.firestationservice.findFireStationDTOByFireStationNumber(number);
    }

    // Get person + age + medicalRecord = fireStation by address
    @GetMapping(value="fire")
    public List<FireDTO> findPersonAndMedicalRecordByAddress (String address) {
        return this.firestationservice.findPersonAndMedicalRecordByFireStationAddress((address));
    }

    // Get a list of family by a list of fireStation
    @GetMapping(value="flood")
    public List<FloodDTO> findFamilyByFireStationList (@RequestParam (name="stations")List<String> fireStation) {
        return this.firestationservice.findfamilyByFireStationList(fireStation);
    }
}
