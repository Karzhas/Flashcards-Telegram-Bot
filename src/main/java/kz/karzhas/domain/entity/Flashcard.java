package kz.karzhas.domain.entity;

public class Flashcard {
    private Long id;
    private String frontside;
    private String backside;
    private int rating;

    public Flashcard(){}

    public Flashcard(String frontside, String backside, int rating) {
        this.frontside = frontside;
        this.backside = backside;
        this.rating = rating;
    }

    public String getFrontside() {
        return frontside;
    }

    public void setFrontside(String frontside) {
        this.frontside = frontside;
    }

    public String getBackside() {
        return backside;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Flashcard(Long id, String frontside, String backside, int rating) {
        this.id = id;
        this.frontside = frontside;
        this.backside = backside;
        this.rating = rating;
    }

    public void setBackside(String backside) {
        this.backside = backside;
    }
}
