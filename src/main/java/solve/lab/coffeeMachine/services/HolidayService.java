package solve.lab.coffeeMachine.services;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
@Service
public class HolidayService {

    private static final String URL = "https://date.nager.at/api/v3/publicholidays/2024/KZ";


    public String isMachineWork(){
        LocalDate localDate = LocalDate.now();
        if (localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return "Today we are not working today is weekend!";
        }

        int hour = LocalTime.now().getHour();
        if (hour < 10 || hour > 17){
            return "We work between this times -> 10:00 and 17:00";
        }



        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, String>> list = restTemplate.getForObject(URL, List.class);
        Map<LocalDate, String> holidays = new HashMap<>();
        for(Map<String, String> map : list){
            String holidayDate = map.get("date");
            holidays.put(LocalDate.parse(holidayDate), map.get("name"));
        }
        if (holidays.containsKey(localDate)){
            return "Today we are not working, today is " + holidays.get(LocalDate.now());
        }
        return null;
    }

}
