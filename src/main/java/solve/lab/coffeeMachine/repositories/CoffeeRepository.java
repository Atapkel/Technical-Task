package solve.lab.coffeeMachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solve.lab.coffeeMachine.models.Coffee;

import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
    Optional<Coffee> findByName(String name);
}
