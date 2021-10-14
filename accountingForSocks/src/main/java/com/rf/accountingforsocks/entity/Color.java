package com.rf.accountingforsocks.entity;

import com.rf.accountingforsocks.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    private UUID id;

    @PrePersist
    private void setId() {
        if(this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Column(name = "name")
    private String name;

    public Color() {
    }

    public Color(UUID id, String name) {
        this.id = id;
        this.name = name;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
