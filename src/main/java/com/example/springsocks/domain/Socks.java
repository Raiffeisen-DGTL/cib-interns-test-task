package com.example.springsocks.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Носки.
 *
 * @author Alexander_Tupchin
 */

@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = "id")
@NoArgsConstructor
@Entity
public class Socks {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Цвет носков.
     */
    String color;

    /**
     * Процентное содержание хлопка в составе носков.
     */
    Integer cottonPart;

    /**
     * Количество пар носков.
     */
    Long quantity;

}
