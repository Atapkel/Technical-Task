package solve.lab.coffeeMachine.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solve.lab.coffeeMachine.dto.CoffeeDTO;
import solve.lab.coffeeMachine.dto.IngredientDTO;
import solve.lab.coffeeMachine.models.Coffee;
import solve.lab.coffeeMachine.models.Ingredient;
import solve.lab.coffeeMachine.services.CoffeesService;
import solve.lab.coffeeMachine.utils.CoffeeAlreadyExistsException;
import solve.lab.coffeeMachine.utils.CoffeeMachineErrorResponse;
import solve.lab.coffeeMachine.utils.CoffeeNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/api/coffee")
public class CoffeesController {
    private final CoffeesService coffeesService;
    private final ModelMapper modelMapper;

    public CoffeesController(CoffeesService coffeesService, ModelMapper modelMapper) {
        this.coffeesService = coffeesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<CoffeeDTO> allCoffee() {
        return coffeesService.allCoffee().stream()
                .map(this::convertToCoffeeDTO).toList();
    }

    @GetMapping("/{name}")
    public CoffeeDTO getCoffee(@PathVariable("name") String name) {
        return convertToCoffeeDTO(coffeesService.findCoffeeByName(name));
    }

    @PostMapping()
    public String saveCoffee(@RequestBody CoffeeDTO coffeeDTO) {
        coffeesService.saveCoffee(convertToCoffee(coffeeDTO));
        return "OK";
    }

    @ExceptionHandler
    private ResponseEntity<CoffeeMachineErrorResponse> handleException(CoffeeNotFoundException e) {
        CoffeeMachineErrorResponse response = new CoffeeMachineErrorResponse(
                "We haven't coffee that you request, check coffee name correctness!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CoffeeMachineErrorResponse> handleException(CoffeeAlreadyExistsException e){
        CoffeeMachineErrorResponse response = new CoffeeMachineErrorResponse(
                "Coffee with this name already exists!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
    }

    private Coffee convertToCoffee(CoffeeDTO coffeeDTO) {
        List<IngredientDTO> ingredientDTOS = coffeeDTO.getIngredients();
        List<Ingredient> ingredients = ingredientDTOS.stream()
                .map(this::convertToIngredient).toList();
        Coffee coffee = modelMapper.map(coffeeDTO, Coffee.class);
        coffee.setIngredients(ingredients);
        ingredients.forEach(ingredient -> ingredient.setCoffee(coffee));
        return coffee;
    }

    private CoffeeDTO convertToCoffeeDTO(Coffee coffee) {
        return modelMapper.map(coffee, CoffeeDTO.class);
    }

    private Ingredient convertToIngredient(IngredientDTO ingredientDTO) {
        return modelMapper.map(ingredientDTO, Ingredient.class);
    }
}
