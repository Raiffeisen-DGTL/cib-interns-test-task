package com.github.furetur.raiffeisentask.restData;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SocksJson(
        @JsonProperty(required = true) String color,
        @JsonProperty(required = true) int cottonPart,
        @JsonProperty(required = true) int quantity
) {}
