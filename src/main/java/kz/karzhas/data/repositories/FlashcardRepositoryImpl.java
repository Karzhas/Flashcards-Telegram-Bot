package kz.karzhas.data.repositories;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.mappers.FlashcardDtoMapper;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.repositories.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlashcardRepositoryImpl implements FlashcardRepository {

    FlashcardJpaRepository flashcardJpaRepository;
    FlashcardDtoMapper mapper;

    @Autowired
    public FlashcardRepositoryImpl(FlashcardJpaRepository flashcardJpaRepository, FlashcardDtoMapper mapper) {
        this.flashcardJpaRepository = flashcardJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Flashcard>> getFlashcards() {
        List<FlashcardDto> flashcardDtos = flashcardJpaRepository.findAll();
        List<Flashcard> flashcards = mapper.dtosToEntities(flashcardDtos);
        return Single.fromObservable(Observable.just(flashcards));
    }

    @Override
    public Single<Flashcard> getFlashcardById(int id) {
        return null;
    }

    @Override
    public Completable saveFlashcard(Flashcard flashcard) {
        flashcardJpaRepository.save(mapper.entityToDto(flashcard));
        return Completable.complete();
    }

    /*@Override
    public Completable updateFlashcard(Flashcard flashcard) {
        FlashcardDto dto = mapper.entityToDto(flashcard);
        flashcardJpaRepository.save(dto);
        return Completable.complete();
    }*/

    @Override
    public Completable deleteFlashcard(int id) {
        return null;
    }
}
