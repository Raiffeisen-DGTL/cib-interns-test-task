package com.home.sock.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "colors")
public class Color {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    @SequenceGenerator(name = "color_generator", sequenceName = "color_seq", allocationSize = 1)
    private long id;

    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sock> socks;
    public long getId() {
        return id;
    }

    public Color() {
    }
    public Color(String color) {
        this.color = color;
    }

    public Color(String color, Set<Sock> socks) {
        this.color = color;
        this.socks = socks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public Set<Sock> getSocks() {
        return socks;
    }

    public void setSocks(Set<Sock> socks) {
        this.socks = socks;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void adSock(Sock sock) {
    this.socks.add(sock);
    }
}
