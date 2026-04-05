package i.forget.springboot.boilerplate.service;

import i.forget.springboot.boilerplate.components.PriceCalculator;
import i.forget.springboot.boilerplate.model.Dish;
import i.forget.springboot.boilerplate.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final PriceCalculator priceCalculator;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll().stream()
                .peek(dish -> {
                    BigDecimal finalPrice = priceCalculator.calculateTotal(dish.getPrice());
                    dish.setPrice(finalPrice);
                })
                //Because peek() is an intermediate operation, it is lazy.
                // If you don't have a terminal operation (like .collect(), .findFirst(), or .count())
                // at the end of your stream, peek() will never run.
                .collect(Collectors.toList());
    }
}