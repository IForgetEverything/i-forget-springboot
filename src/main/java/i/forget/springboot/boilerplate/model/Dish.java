package i.forget.springboot.boilerplate.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
//It creates a "blank" instance using the No-Args Constructor.
//It then uses Reflection to "inject" the values into the fields.
//Without this: Your Spring Boot app will crash with a NoSuchMethodException or a HibernateException because it can't find a way to "instantiate" your Entity.
@AllArgsConstructor // 2. Used by you for easy object creation, creates a constructor that accepts every field in the class.
@Builder // Great for creating test data!
public class Dish {
    /**
     * The Rule for 2026:
     * If you add @AllArgsConstructor, you must explicitly add @NoArgsConstructor if the class is a JPA @Entity.
     * If you forget the "No-Args" one, your database queries will fail.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
}