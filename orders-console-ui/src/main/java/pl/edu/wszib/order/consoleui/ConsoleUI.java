package pl.edu.wszib.order.consoleui;

import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.order.InMemoryOrderRepository;
import pl.edu.wszib.order.application.order.OrderModule;
import pl.edu.wszib.order.application.product.InMemoryProductRepository;
import pl.edu.wszib.order.application.product.ProductModule;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        final ProductApi chocolate = new ProductApi(UUID.randomUUID().toString(),
                "Czekolada",
                BigDecimal.valueOf(4));
        final ProductApi cocaCola = new ProductApi(UUID.randomUUID().toString(),
                "Coca-cola",
                BigDecimal.valueOf(5));

        final ProductModule productModule = new ProductModule(new InMemoryProductRepository());
        productModule.getFacade().create(chocolate);
        productModule.getFacade().create(cocaCola);

        final OrderModule orderModule = new OrderModule(new InMemoryOrderRepository(), productModule.getFacade());
        new OrderMenuView(scanner, orderModule.getFacade(), productModule.getFacade()).open();
    }

    // 1. Utwórz zamówienie
    // 2. Wyszukaj zamówienie
    // 3. Dodaj produkt do zamówienia
    // 4. Usuń produkt za zamówienia
    // 0. Wyjście

    // 1:
    // Wyświetl id utworzonego zamówienia

    // 2:
    // Wprowadź id zamówienia: ${id}
    // Wyświetl zamówienie na ekranie

    // 3:
    // Wprowadź id zamówienia: ${id}
    // Wyświetl listę dostępnych produktów

    // 4:
    // Wprowadź id zamówienia: ${id}
    // Wyświetl aktualną listę produktów
    // Wybierz produkt do usunięcia

    // 0. Wyjście
    // Zakońćzenie działania aplikacji
}
