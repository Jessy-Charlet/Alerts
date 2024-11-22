package SafetyNet.Alerts.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class FloodDTO {

    private String fireStation;
    private List<FamilyDTO> familyList;
}
