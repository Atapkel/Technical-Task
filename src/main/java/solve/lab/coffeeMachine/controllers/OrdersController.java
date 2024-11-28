package solve.lab.coffeeMachine.controllers;

import org.springframework.web.bind.annotation.*;
import solve.lab.coffeeMachine.dto.QuantityDTO;
import solve.lab.coffeeMachine.services.CoffeesService;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    private final CoffeesService coffeesService;

    public OrdersController(CoffeesService coffeesService) {
        this.coffeesService = coffeesService;
    }

    @PatchMapping("/{name}")
    public String makeOrder(@PathVariable("name") String name,
                            @RequestBody QuantityDTO quantityDTO){
        System.out.println("I need " + quantityDTO.getQuantity() + " coffees need with name: " + name);
        return coffeesService.makeOrder(name, quantityDTO.getQuantity());
    }
}
