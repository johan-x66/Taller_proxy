package Taller_proxy;
public class Main {
    public static void main(String[] args) {
        ShippingCalculator smart = new SmartShippingCalculator();
        ShippingCalculator proxy = new ShippingCalculatorProxy(smart);
        Checkout checkout = new Checkout(proxy);

        Order order = new Order("1", "cust1", "Bogotá", 100.0, 2.0);
        System.out.println("Total: " + checkout.total(order));
    }
}