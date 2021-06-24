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

    public FlashcardDto(){}

    public FlashcardDto(String frontside, String backside) {
        this.frontside = frontside;
        this.backside = backside;
    }

    public FlashcardDto(Long id, String frontside, String backside) {
        this.id = id;
        this.frontside = frontside;
        this.backside = backside;
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
