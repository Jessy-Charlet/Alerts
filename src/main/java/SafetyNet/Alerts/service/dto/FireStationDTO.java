package SafetyNet.Alerts.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class FireStationDTO {

    private List<PersonDTO> personDTOS;
    private int numberOfAdult;
    private int numberOfChild;



}
