package solve.lab.coffeeMachine.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeMachineErrorResponse {
    private String message;
    private long timestamp;
}
