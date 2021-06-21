package kz.karzhas.domain.usecases;

import io.reactivex.Single;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.repositories.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllFlashcardsUseCase implements BaseUseCase< Single<List<Flashcard>> >{

    @Autowired
    FlashcardRepository flashcardRepository;

    public GetAllFlashcardsUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }


    @Override
    public Single<List<Flashcard>> execute() {
        return flashcardRepository.getFlashcards();
    }
}
