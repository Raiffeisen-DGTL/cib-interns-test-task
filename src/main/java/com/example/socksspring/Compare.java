package com.example.socksspring;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Compare {
    MoreThan("moreThan"),
    LessThan("lessThan"),
    Equals("equals");

    private String name;

    private static final Map<String, Compare> ENUM_MAP;

    Compare (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Build an immutable map of String name to enum pairs.
    // Any Map impl can be used.
    static {
        Map<String,Compare> map = new ConcurrentHashMap<String, com.example.socksspring.Compare>();
        for (Compare instance : Compare.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Compare get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
