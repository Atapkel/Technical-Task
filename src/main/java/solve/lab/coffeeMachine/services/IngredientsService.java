package solve.lab.coffeeMachine.services;

import org.springframework.stereotype.Service;
import solve.lab.coffeeMachine.repositories.IngredientsRepository;

@Service
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

}
