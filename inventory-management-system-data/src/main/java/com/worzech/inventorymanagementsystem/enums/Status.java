package com.worzech.inventorymanagementsystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    NEW("Nowy"),
    DEMAND("Zapotrzebowany"),
    ORDERED("Zamówiony"),
    DELIVERED("Dostarczony"),
    CANCELLED("Anulowany");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    @JsonCreator
    public static Status decode(final String code) {
        return Stream.of(Status.values()).filter(
                targetEnum -> targetEnum.code.equals(
                        code)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
