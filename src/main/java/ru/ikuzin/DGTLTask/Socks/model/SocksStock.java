package ru.ikuzin.DGTLTask.Socks.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "socks_stock")
@NoArgsConstructor
public class SocksStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_id")
    private Integer stockId;

    @Column(name = "count")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color", nullable = false)
    private SocksColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cotton_part", nullable = false)
    private SocksCottonPart part;

    public SocksStock(Integer count, SocksColor color, SocksCottonPart cottonPart) {
        this.count = count;
        this.color = color;
        this.part = cottonPart;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}


