package pl.edu.wszib.order.consoleui;

import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.order.OrderFacade;
import pl.edu.wszib.order.application.product.ProductFacade;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class OrderMenuView {
    private final Scanner scanner;
    private final OrderMenuController controller;

    public OrderMenuView(final Scanner scanner,
                         final OrderFacade orderFacade,
                         final ProductFacade productFacade) {
        this.scanner = scanner;
        this.controller = new OrderMenuController(this, orderFacade, productFacade);
    }

    boolean open() {
        return Stream.generate(this::option)
                .anyMatch(OrderMenuOption::isExit); // czekamy na opcję "Exit"
    }

    private OrderMenuOption option() {
        printOptions();
        final OrderMenuOption option = readOption();
        final OrderApiResult result = controller.handle(option);
        printResult(result);
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

    private void printResult(final OrderApiResult result) {
        if (result.isSuccess()) {
            println("Szcegóły zamówienia: " + result.getOrder());
        } else {
            println("Wystąpił błąd! " + result.getError());
        }
    }

    private void println(final String text) {
        System.out.println(text);
    }

    public String getProduct(final Set<ProductApi> products) {
        products.forEach(this::printProduct);
        return scanner.nextLine();
    }

    private void printProduct(final ProductApi product) {
        println("Id: " + product.getId() + " Name: " + product.getName() + " Price: " + product.getPrice());
    }

    public void sayGoodbye() {
        println("Do widzenia!");
    }
}
