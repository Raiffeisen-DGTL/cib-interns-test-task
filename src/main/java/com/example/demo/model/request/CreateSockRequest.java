package com.example.demo.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateSockRequest {
//    private Long id;
    private String color;
    private Integer cottonPart;
    private Integer quantity;
}
