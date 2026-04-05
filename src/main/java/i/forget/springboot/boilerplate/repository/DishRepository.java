package i.forget.springboot.boilerplate.repository;

import i.forget.springboot.boilerplate.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    // Spring Data JPA writes the SQL for this automatically!
}