package Taller_proxy;
public class Main {
    public static void main(String[] args) {
        FeatureFlags flags = new FeatureFlags();
        ShippingCalculator legacy = new LegacyShippingCalculator();
        ShippingCalculator smart = new SmartShippingCalculator();
        ShippingCalculator proxy = new ShippingCalculatorProxy(legacy, smart, flags);

        Checkout checkout = new Checkout(proxy);

        flags.enable("smart-shipping");

        Order order = new Order("1", "cust1", "Bogotá", 100.0, 2.0);
        System.out.println("Total: " + checkout.total(order));
    }
}