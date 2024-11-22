package SafetyNet.Alerts.controller;


import SafetyNet.Alerts.service.PersonService;
import SafetyNet.Alerts.service.dto.ChildAlertDTO;
import SafetyNet.Alerts.service.dto.PersonInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController (PersonService personService){
        this.personService = personService;
    }

    //Get a email list
    @GetMapping(value = "communityEmail")
    public List<String> EmailsList(@RequestParam(name="city") String city){
        return this.personService.findAllCityByEmail(city);
    }

    //Get a list of child and his family
    @GetMapping(value = "childAlert")
    public List<ChildAlertDTO> childAlertDTOList(@RequestParam(name="address") String address){
        return this.personService.findAllChildByAddress(address);
    }

    //Get a list of info person by firstName and lastName
    @GetMapping(value = "personInfo")
    public List<PersonInfoDTO> personInfoList(@RequestParam(name="firstName") String firstName,
                                              @RequestParam(name="lastName") String lastName) {
        return this.personService.findAllInfoByName(lastName, firstName);
    }

}
