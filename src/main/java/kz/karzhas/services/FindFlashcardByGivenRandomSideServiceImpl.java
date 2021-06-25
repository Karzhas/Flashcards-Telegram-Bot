package kz.karzhas.services;

import io.reactivex.Single;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.usecases.FindFlashcardByGivenRandomSideUseCase;
import kz.karzhas.mappers.FlashcardDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindFlashcardByGivenRandomSideServiceImpl implements FindFlashcardByGivenRandomSideService{

    FindFlashcardByGivenRandomSideUseCase flashcardByGivenRandomSideUseCase;
    FlashcardDtoMapper mapper;

    @Autowired
    public FindFlashcardByGivenRandomSideServiceImpl(FindFlashcardByGivenRandomSideUseCase flashcardByGivenRandomSideUseCase, FlashcardDtoMapper mapper) {
        this.flashcardByGivenRandomSideUseCase = flashcardByGivenRandomSideUseCase;
        this.mapper = mapper;
    }

    @Override
    public Single<FlashcardDto> findFlashcardByGivenRandomSide(String randomSide) {
        return Single.create(singleEmitter -> {
            flashcardByGivenRandomSideUseCase.execute(randomSide).subscribe(findedFlashcard -> {
               FlashcardDto dto = mapper.entityToDto(findedFlashcard);
               singleEmitter.onSuccess(dto);
            });
        });
    }
}
