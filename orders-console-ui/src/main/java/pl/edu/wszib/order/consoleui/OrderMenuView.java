package pl.edu.wszib.order.consoleui;

import pl.edu.wszib.order.api.OrderApi;
import pl.edu.wszib.order.application.product.Product;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class OrderMenuView {
    private final Scanner scanner;
    private final OrderMenuController controller;

    public OrderMenuView(final Scanner scanner) {
        this.scanner = scanner;
        this.controller = new OrderMenuController(this);
    }

    boolean open() {
        return Stream.generate(this::option)
                .anyMatch(OrderMenuOption::isExit); // czekamy na opcję "Exit"
    }

    private OrderMenuOption option() {
        printOptions();
        final OrderMenuOption option = readOption();
        final OrderApi result = controller.handle(option);
        printOrder(result);
        return option;
    }

    private void printOptions() {
        OrderMenuOption.valuesStream()
                .forEachOrdered(menuItem -> println(menuItem.getMessage()));
    }

    private OrderMenuOption readOption() {
        println("Wybieram opcję: ");
        return OrderMenuOption.parse(scanner.nextLine())
                .orElseGet(this::readOption);   //wywołaj ponownie
    }

    public String getOrderId() {
        println("Wprowadź id zamówienia: ");
        return scanner.nextLine();
    }

    private void printOrder(final OrderApi order) {
        println("Identyfikator zamówienia: " + order.getId());
    }

    private void println(final String text) {
        System.out.println(text);
    }

    public String getProduct(final Set<Product> products) {
        products.forEach(this::printProduct);
        return scanner.nextLine();
    }

    private void printProduct(final Product product) {
        println("Id: " + product.getId() + " Name: " + product.getName() + " Price: " + product.getPrice());
    }

    public void sayGoodbye() {
        println("Do widzenia!");
    }
}
