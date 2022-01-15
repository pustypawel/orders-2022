package pl.edu.wszib.order.consoleui;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

enum OrderMenuOption {
    CREATE_ORDER("1", "Utwórz zamówienie"),
    GET_ORDER("2", "Wyszukaj zamówienie"),
    ADD_ITEM_TO_ORDER("3", "Dodaj produkt do zamówienia"),
    REMOVE_ITEM_FROM_ORDER("4", "Usuń produkt z zamówienia"),
    EXIT("0", "Wyjście"),
    ;
    private final String id;
    private final String description;
    private final String message;

    OrderMenuOption(String id, String description) {
        this.id = id;
        this.description = description;
        this.message = id + ". " + description;
    }

    public String getMessage() {
        return message;
    }

    public static Optional<OrderMenuOption> parse(final String id) {
        return valuesStream()
                .filter(option -> Objects.equals(id, option.id))
                .findFirst();
    }

    public static Stream<OrderMenuOption> valuesStream() {
        return Stream.of(values());
    }

    public boolean isExit() {
        return this == EXIT;
    }
}
