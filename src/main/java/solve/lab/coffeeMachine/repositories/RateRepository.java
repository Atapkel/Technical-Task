package solve.lab.coffeeMachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solve.lab.coffeeMachine.models.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

}
