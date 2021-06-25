package kz.karzhas.services;

import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;

public interface SaveFlashcardService {
    void saveFlashcard(FlashcardDto flashcardDto);
}
