package com.example.testtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.FieldResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocksDto {

    @NonNull
    Long id;

    @NonNull
    //@JsonProperty("color")
    String color;

    @NonNull
    //@JsonProperty("cotton")
    Double cotton;
}
