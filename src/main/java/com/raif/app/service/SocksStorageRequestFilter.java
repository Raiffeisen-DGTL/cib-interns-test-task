package com.raif.app.service;

import com.raif.app.service.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocksStorageRequestFilter {
    private String color;
    private OperationType operation;
    private Integer cottonPart;
}
