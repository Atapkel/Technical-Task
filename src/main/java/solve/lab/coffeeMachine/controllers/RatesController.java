package solve.lab.coffeeMachine.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solve.lab.coffeeMachine.dto.RateDTO;
import solve.lab.coffeeMachine.models.Rate;
import solve.lab.coffeeMachine.services.RatesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RatesController {
    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping
    public List<RateDTO> showAllRates(){
        List<Rate> rates =  ratesService.showAllRates();
        List<RateDTO> rateDTOS = new ArrayList<>(rates.stream().map(rate -> {
            RateDTO rateDTO = new RateDTO();
            rateDTO.setCoffeeName(rate.getCoffee().getName());
            rateDTO.setNumberOrder(rate.getOrderNumber());
            return rateDTO;
        }).toList());
        Collections.sort(rateDTOS);
        return rateDTOS;
    }
}
