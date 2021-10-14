package com.raif.storage.sock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SockDto {
    private String color;
    private int cottonPart = -1;
    private int quantity = -1;
}
