package kz.karzhas.domain.usecases;

import io.reactivex.Completable;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.repositories.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnCorrectAnswerToFlashcardUseCase implements BaseUseCaseWithParam<Integer, Completable> {

    @Autowired
    FlashcardRepository flashcardRepository;

    public OnCorrectAnswerToFlashcardUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @Override
    public Completable execute(Integer integer) {
        return null;
    }
}
