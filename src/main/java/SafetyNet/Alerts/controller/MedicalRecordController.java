package SafetyNet.Alerts.controller;

import SafetyNet.Alerts.service.MedicalRecordService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;

    }
}
