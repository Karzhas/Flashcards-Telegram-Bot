package kz.karzhas.domain.usecases;

import io.reactivex.Completable;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.repositories.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddNewFlashcardUseCase implements BaseUseCaseWithParam<Flashcard, Completable>  {
    @Autowired
    FlashcardRepository flashcardRepository;

    public AddNewFlashcardUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @Override
    public Completable execute(Flashcard flashcard) {
        return flashcardRepository.saveFlashcard(flashcard);
    }
}
