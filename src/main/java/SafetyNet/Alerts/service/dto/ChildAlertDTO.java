package SafetyNet.Alerts.service.dto;

import SafetyNet.Alerts.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class ChildAlertDTO {

    private String firstName;
    private String lastname;
    private int age;
    private List<Person> family;

}
