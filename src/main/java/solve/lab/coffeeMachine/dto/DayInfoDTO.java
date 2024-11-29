package solve.lab.coffeeMachine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DayInfoDTO {
    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private List<String> counties;
    private Integer launchYear;
    private List<String> types;
}
