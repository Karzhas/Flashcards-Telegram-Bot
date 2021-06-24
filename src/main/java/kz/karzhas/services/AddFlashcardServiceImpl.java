package kz.karzhas.services;


import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.usecases.AddNewFlashcardUseCase;
import kz.karzhas.mappers.FlashcardDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddFlashcardServiceImpl implements AddFlashcardService {

    AddNewFlashcardUseCase addNewFlashcardUseCase;
    FlashcardDtoMapper mapper;

    @Autowired
    public AddFlashcardServiceImpl(AddNewFlashcardUseCase addNewFlashcardUseCase, FlashcardDtoMapper mapper) {
        this.addNewFlashcardUseCase = addNewFlashcardUseCase;
        this.mapper = mapper;
    }

    @Override
    public void addFlashcard(FlashcardDto flashcardDto) {
        Flashcard flashcard = mapper.dtoToEntity(flashcardDto);
        addNewFlashcardUseCase.execute(flashcard);
    }
}
