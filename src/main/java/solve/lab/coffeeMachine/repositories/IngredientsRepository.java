package solve.lab.coffeeMachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solve.lab.coffeeMachine.models.Ingredient;
@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient,Integer> {

}
