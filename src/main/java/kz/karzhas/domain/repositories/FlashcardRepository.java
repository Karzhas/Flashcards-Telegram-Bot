package kz.karzhas.domain.repositories;

import io.reactivex.Completable;
import io.reactivex.Single;
import kz.karzhas.domain.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository {

    Single<List<Flashcard>> getFlashcards();
    Single<Flashcard> getFlashcardById(int id);
    Completable saveFlashcard(Flashcard flashcard);
    Completable deleteFlashcard(int id);
}
