package i.forget.springboot.boilerplate.repository;

import i.forget.springboot.boilerplate.model.Dish;
import i.forget.springboot.boilerplate.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * By default, @DataJpaTest tries to create its own "internal" H2 database and ignores your application-test.yml.
 * However, because you have Flyway in your project, Flyway wants to control the database itself. They end up fighting over the connection.
 * The Fix: Add @AutoConfigureTestDatabase to your test class to tell Spring: "Don't replace my database; use the one I defined in my properties."
 */
@DataJpaTest
@ActiveProfiles("test") // Uses your H2 application-test.yml
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DishRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Test
    void shouldSaveAndFindDish() {
        // Arrange
        Dish ramen = Dish.builder()
                .name("Tonkotsu Ramen")
                .price(new BigDecimal("14.50"))
                .build();

        // Act
        Dish saved = dishRepository.save(ramen);

        // Assert
        assertThat(saved.getId()).isNotNull();
        assertThat(dishRepository.findById(saved.getId())).isPresent();
    }
}