package ru.danilarassokhin.raiffeisensocks.dto;

public class HomePageDto {
    private String author;
    private String repo;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }
}
