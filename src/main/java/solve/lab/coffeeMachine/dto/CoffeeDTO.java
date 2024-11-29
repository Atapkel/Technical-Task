package solve.lab.coffeeMachine.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class CoffeeDTO {
    private String name;
    private List<IngredientDTO> ingredients;
}
