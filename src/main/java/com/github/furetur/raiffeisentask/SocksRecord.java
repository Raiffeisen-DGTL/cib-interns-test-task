package com.github.furetur.raiffeisentask;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SocksRecord(
        @JsonProperty(required = true) String color,
        @JsonProperty(required = true) int cottonPart,
        @JsonProperty(required = true) int quantity
) {}
