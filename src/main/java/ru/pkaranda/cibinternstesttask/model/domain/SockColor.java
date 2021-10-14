package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sock_colors")
@Builder
@NoArgsConstructor
public class SockColor {

    @Id
    @SequenceGenerator(sequenceName = "seq_sock_color_id", name = "sock_color_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sock_color_id_gen")
    private Long id;

    @Column(name = "color", unique = true)
    private String color;

}
