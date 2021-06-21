package kz.karzhas.data.mappers;

import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlashcardDtoMapper {

    public List<Flashcard> dtosToEntities(List<FlashcardDto> dtos){
        return dtos
                .stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());

    }

    public List<FlashcardDto> entitiesToDto(List<Flashcard> entities){
        return entities
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

    }


    public Flashcard dtoToEntity(FlashcardDto flashcardDto){
        Flashcard flashcard = new Flashcard();
        flashcard.setBackside(flashcardDto.getBackside());
        flashcard.setFrontside(flashcardDto.getFrontside());
        return flashcard;
    }

    public FlashcardDto entityToDto(Flashcard flashcard){
        FlashcardDto flashcardDto = new FlashcardDto();
        flashcardDto.setBackside(flashcard.getBackside());
        flashcardDto.setFrontside(flashcard.getFrontside());
        return flashcardDto;
    }
}
