package solve.lab.coffeeMachine.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rates")
@Getter
@Setter
public class Rate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_number")
    private int orderNumber;
    @OneToOne
    @JoinColumn(name = "coffee_id", referencedColumnName = "id", nullable = false)
    private Coffee coffee;
}
