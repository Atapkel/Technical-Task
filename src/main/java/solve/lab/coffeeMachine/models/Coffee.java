package solve.lab.coffeeMachine.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "coffee")
@Getter
@Setter
@NoArgsConstructor
public class Coffee implements Comparable<Coffee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "coffee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;
    @OneToOne(mappedBy = "coffee", cascade = CascadeType.ALL, orphanRemoval =  true)
    private Rate rate;

    @Override
    public int compareTo(Coffee o) {
        return o.rate.getOrderNumber() - this.rate.getOrderNumber();
    }
}
