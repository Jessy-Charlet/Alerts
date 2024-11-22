package SafetyNet.Alerts.service.dto;

import SafetyNet.Alerts.model.MedicalRecord;
import lombok.Data;

@Data
public class FireDTO {

    private int fireStationNumber;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String[] medications;
    private String[] allergies;
}
