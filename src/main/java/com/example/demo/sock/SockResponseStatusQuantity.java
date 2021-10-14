package com.example.demo.sock;

import java.util.Optional;

public class SockResponseStatusQuantity extends SockResponseStatus{
    private Optional<String> quantity;

    public SockResponseStatusQuantity(Integer status,Optional<String> quantity) {
        this.setStatus(status);
        this.quantity = quantity;
    }

    public Optional<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(Optional<String> quantity) {
        this.quantity = quantity;
    }
}
