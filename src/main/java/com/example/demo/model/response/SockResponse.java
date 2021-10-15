package com.example.demo.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SockResponse {
//    private Long id;
    private String color;
    private Integer cottonPart;
    private Integer quantity;
}
