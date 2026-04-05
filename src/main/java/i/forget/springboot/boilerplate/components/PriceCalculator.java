package i.forget.springboot.boilerplate.components;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@NoArgsConstructor
public class PriceCalculator {

    //Would be better to store in environment-specific data for easy management. This is just for demonstration purposes
    private static final BigDecimal TAX_RATE = new BigDecimal("0.07");
    private static final BigDecimal GST_RATE = new BigDecimal("0.10");

    //BigDecimal does not support standard math operators (+, -, *, /).
    public BigDecimal calculateTotal(BigDecimal basePrice) {
        // 1. Calculate Tax (basePrice * 0.07)
        BigDecimal totalTax = basePrice.multiply(TAX_RATE);

        // 2. Calculate GST (basePrice * 0.10)
        BigDecimal totalGst = basePrice.multiply(GST_RATE);

        // 3. Sum them up: base + tax + gst
        BigDecimal grandTotal = basePrice.add(totalTax).add(totalGst);

        // 4. Round to 2 decimal places for the final bill
        //$10.654 becomes $10.65
        //$10.655 becomes $10.66
        return grandTotal.setScale(2, RoundingMode.HALF_UP);
    }
}