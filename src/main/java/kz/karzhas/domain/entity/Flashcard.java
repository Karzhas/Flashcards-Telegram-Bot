package kz.karzhas.domain.entity;

public class Flashcard {
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

    public void setBackside(String backside) {
        this.backside = backside;
    }
}
