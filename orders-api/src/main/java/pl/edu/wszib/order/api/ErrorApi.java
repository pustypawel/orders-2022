package pl.edu.wszib.order.api;

import lombok.Value;

@Value
public class ErrorApi {
    String code;
    String message;
}
