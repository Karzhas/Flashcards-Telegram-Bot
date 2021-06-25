package kz.karzhas.data.dto;

import javax.persistence.*;

@Entity
@Table(name = "flashcard")
public class FlashcardDto {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "frontside")
    private String frontside;

    @Column(name = "backside")
    private String backside;

    @Column(name = "rating")
    private int rating;

    public FlashcardDto(){}

    public FlashcardDto(String frontside, String backside) {
        this.frontside = frontside;
        this.backside = backside;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public FlashcardDto(Long id, String frontside, String backside) {
        this.id = id;
        this.frontside = frontside;
        this.backside = backside;
    }

    public FlashcardDto(Long id, String frontside, String backside, int rating) {
        this.id = id;
        this.frontside = frontside;
        this.backside = backside;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrontside() {
        return frontside;
    }

    public void setFrontside(String firstName) {
        this.frontside = firstName;
    }

    public String getBackside() {
        return backside;
    }

    public void setBackside(String lastName) {
        this.backside = lastName;
    }
}
