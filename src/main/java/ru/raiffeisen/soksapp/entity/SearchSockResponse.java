package ru.raiffeisen.soksapp.entity;

public class SearchSockResponse {

    private int count;

    public SearchSockResponse() {
    }

    public SearchSockResponse(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

