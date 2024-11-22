package SafetyNet.Alerts.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String[] medications;
    private String[] allergies;

    // get age of person
    public int getAge(String date) {
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();
        return Math.abs(Period.between(birthDate, now).getYears());

    }
}
