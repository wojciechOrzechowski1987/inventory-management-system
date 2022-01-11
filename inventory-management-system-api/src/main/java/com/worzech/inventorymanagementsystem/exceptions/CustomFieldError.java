package com.worzech.inventorymanagementsystem.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomFieldError {
        private String field;
        private String message;
}
