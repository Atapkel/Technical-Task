package solve.lab.coffeeMachine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solve.lab.coffeeMachine.dto.QuantityDTO;
import solve.lab.coffeeMachine.services.CoffeesService;
import solve.lab.coffeeMachine.services.HolidayService;
import solve.lab.coffeeMachine.utils.CoffeeMachineErrorResponse;
import solve.lab.coffeeMachine.utils.CoffeeMachineNotWorkException;
import solve.lab.coffeeMachine.utils.CoffeeNotFoundException;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    private final CoffeesService coffeesService;
    private final HolidayService holidayService;

    public OrdersController(CoffeesService coffeesService, HolidayService holidayService) {
        this.coffeesService = coffeesService;
        this.holidayService = holidayService;
    }

    @PatchMapping("/{name}")
    public String makeOrder(@PathVariable("name") String name,
                            @RequestBody QuantityDTO quantityDTO){
        String message = holidayService.isMachineWork();
        if (message != null){
            throw new CoffeeMachineNotWorkException(message);
        }
        return coffeesService.makeOrder(name, quantityDTO.getQuantity());
    }

    @ExceptionHandler
    private ResponseEntity<CoffeeMachineErrorResponse> handleException(CoffeeNotFoundException e){
        CoffeeMachineErrorResponse response = new CoffeeMachineErrorResponse(
                "Coffee that you want order, is not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CoffeeMachineErrorResponse> handleException(CoffeeMachineNotWorkException e){
        CoffeeMachineErrorResponse response = new CoffeeMachineErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }
}
