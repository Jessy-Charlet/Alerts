package SafetyNet.Alerts.service.dto;

import lombok.Data;

@Data
public class FamilyDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String[] medications;
    private String[] allergies;
}
