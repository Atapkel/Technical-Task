package solve.lab.coffeeMachine.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solve.lab.coffeeMachine.models.Coffee;
import solve.lab.coffeeMachine.models.Ingredient;
import solve.lab.coffeeMachine.models.Rate;
import solve.lab.coffeeMachine.models.Resource;
import solve.lab.coffeeMachine.repositories.CoffeeRepository;
import solve.lab.coffeeMachine.repositories.IngredientsRepository;
import solve.lab.coffeeMachine.utils.CoffeeNotFoundException;

import java.util.*;

@Service
public class CoffeesService {
    private final CoffeeRepository coffeeRepository;
    private final IngredientsRepository ingredientsRepository;
    private final ResourcesService resourcesService;
    private final RatesService ratesService;

    public CoffeesService(CoffeeRepository coffeeRepository, IngredientsRepository ingredientsRepository, ResourcesService resourcesService, RatesService ratesService) {
        this.coffeeRepository = coffeeRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.resourcesService = resourcesService;
        this.ratesService = ratesService;
    }
    @Transactional
    public void saveCoffee(Coffee coffee){
        Optional<Coffee> checkCoffee = coffeeRepository.findByName(coffee.getName());
        if (checkCoffee.isPresent()){
            throw new RuntimeException();
        }
        coffeeRepository.save(coffee);
        Rate rate = new Rate();
        rate.setOrderNumber(0);
        rate.setCoffee(coffee);
        coffee.setRate(rate);
        ratesService.save(rate);

        for(Ingredient ingredient : coffee.getIngredients()){
            ingredient.setCoffee(coffee);
            ingredientsRepository.save(ingredient);
        }
    }
    public List<Coffee> allCoffee(){
        List<Coffee> allCoffee = coffeeRepository.findAll();
        Collections.sort(allCoffee);
        return allCoffee;
    }

    public Coffee findCoffeeByName(String name) {
        Optional<Coffee> coffee = coffeeRepository.findByName(name);
        return coffee.orElseThrow(CoffeeNotFoundException::new);
    }

    public String makeOrder(String name, int quantity) {
        Optional<Coffee> checkCoffee = coffeeRepository.findByName(name);
        if (checkCoffee.isEmpty()){
            throw new CoffeeNotFoundException();
        }
        Coffee coffee = checkCoffee.get();
        List<Ingredient> ingredients = coffee.getIngredients();
        StringBuilder response = new StringBuilder();
        boolean check = false;
        for(Ingredient ingredient : ingredients){
            Resource resource = resourcesService.findByName(ingredient.getName());
            if (resource == null){
                response.append(ingredient.getName()).append(" is not in our resources\n");
                check = true;
            }
            if (resource != null && resource.getQuantity() < (long) quantity * ingredient.getQuantity()){
                response.append(String.format("Resources not enough\n" +
                        "Name of resource: %s, how much we need: %s", resource.getName(),
                        (long) quantity * ingredient.getQuantity() - resource.getQuantity())).append("\n");
                check = true;
            }
        }
        if (check)return response.toString();
        for(Ingredient ingredient : ingredients){
            resourcesService.useQuantity(ingredient.getName(),quantity * ingredient.getQuantity());
        }
        Rate rate = coffee.getRate();
        rate.setOrderNumber(rate.getOrderNumber() + 1);
        ratesService.save(rate);


        return "Your coffee is ready: count is " + quantity + " " + coffee.getName();

    }



}
