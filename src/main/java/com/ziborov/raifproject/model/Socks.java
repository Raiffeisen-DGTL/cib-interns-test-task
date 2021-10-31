package com.ziborov.raifproject.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "socks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Socks {

    public enum SocksColor {
        RED("red"),
        GREEN("green"),
        BLUE("blue"),
        BLACK("black"),
        WHITE("white");

        private final String fieldName;

        SocksColor(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public static SocksColor fromString(String color) {
            for (SocksColor socksColor : SocksColor.values()) {
                if (socksColor.getFieldName().equals(color)) {
                    return socksColor;
                }
            }

            return null;
        }
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private SocksColor color;

    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

}