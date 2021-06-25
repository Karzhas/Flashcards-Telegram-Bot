package kz.karzhas.services;


import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.usecases.AddNewFlashcardUseCase;
import kz.karzhas.mappers.FlashcardDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveFlashcardServiceImpl implements SaveFlashcardService {

    AddNewFlashcardUseCase addNewFlashcardUseCase;
    FlashcardDtoMapper mapper;

    @Autowired
    public SaveFlashcardServiceImpl(AddNewFlashcardUseCase addNewFlashcardUseCase, FlashcardDtoMapper mapper) {
        this.addNewFlashcardUseCase = addNewFlashcardUseCase;
        this.mapper = mapper;
    }

    @Override
    public void saveFlashcard(FlashcardDto flashcardDto) {
        Flashcard flashcard = mapper.dtoToEntity(flashcardDto);
        addNewFlashcardUseCase.execute(flashcard);
    }
}
