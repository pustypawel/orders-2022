package pl.edu.wszib.order.consoleui;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        new OrderMenuView(scanner).open();
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
