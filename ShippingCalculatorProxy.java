package Taller_proxy;

import java.util.HashMap;
import java.util.Map;

public class ShippingCalculatorProxy implements ShippingCalculator {
    private final ShippingCalculator legacy;
    private final ShippingCalculator smart;
    private final FeatureFlags flags;
    private final Map<String, Double> cache = new HashMap<>();

    public ShippingCalculatorProxy(ShippingCalculator legacy,
                                   ShippingCalculator smart,
                                   FeatureFlags flags) {
        this.legacy = legacy;
        this.smart = smart;
        this.flags = flags;
    }

    @Override
    public double calculate(Order order) {
        if (cache.containsKey(order.id())) {
            return cache.get(order.id());
        }
        double result = flags.isEnabled("smart-shipping")
                ? smart.calculate(order)
                : legacy.calculate(order);
        cache.put(order.id(), result);
        return result;
    }
}
