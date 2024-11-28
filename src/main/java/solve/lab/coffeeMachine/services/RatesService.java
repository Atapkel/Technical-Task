package solve.lab.coffeeMachine.services;

import org.springframework.stereotype.Service;
import solve.lab.coffeeMachine.models.Rate;
import solve.lab.coffeeMachine.repositories.RateRepository;

@Service
public class RatesService {
    private final RateRepository rateRepository;

    public RatesService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public void save(Rate rate){
        rateRepository.save(rate);
    }
}
