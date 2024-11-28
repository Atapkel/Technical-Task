package solve.lab.coffeeMachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import solve.lab.coffeeMachine.models.Resource;

import java.util.Optional;

public interface ResourcesRepository extends JpaRepository<Resource, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Resource R SET R.quantity = R.quantity + :quantity WHERE R.name=:name")
    void addQuantity(@Param("name") String name,@Param("quantity") long quantity);
    Optional<Resource> findByName(String name);
    @Transactional
    @Modifying
    @Query("UPDATE Resource R SET R.quantity = R.quantity - :quantity WHERE R.name=:name")
    void useQuantity(String name,long quantity);
}
