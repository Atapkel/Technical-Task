package solve.lab.coffeeMachine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateDTO implements Comparable<RateDTO>{
    private int numberOrder;
    private String coffeeName;

    @Override
    public int compareTo(RateDTO o) {
        return  o.numberOrder - this.numberOrder;
    }
}
