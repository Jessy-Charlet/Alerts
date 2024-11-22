package SafetyNet.Alerts.service.dto;

import lombok.Data;

@Data
public class PersonInfoDTO {


    private String firstName;
    private String lastName;
    private String address;
    private String eMail;
    private int age;
    private String[] medications;
    private String[] allergies;
}
