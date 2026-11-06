package Taller_proxy;

import java.util.HashMap;
import java.util.Map;

public class ShippingCalculatorProxy implements ShippingCalculator {
    private final ShippingCalculator smart;
    private final Map<String, Double> cache = new HashMap<>();

    public ShippingCalculatorProxy(ShippingCalculator smart) {
        this.smart = smart;
    }

    @Override
    public double calculate(Order order) {
        if (cache.containsKey(order.id())) {
            return cache.get(order.id());
        }

        double result = smart.calculate(order);
        cache.put(order.id(), result);
        return result;
    }
}
