package com.example.testtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.FieldResult;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocksDto {

//    @NonNull
//    Long id;

    @NonNull
    //@JsonProperty("color")
    String color;

    @NonNull
    //@JsonProperty("cotton")
    Integer cottonPart;

//    //@JsonProperty("created_at")
//    Instant createdAt;

    @NonNull
    Long quantity ;
}
