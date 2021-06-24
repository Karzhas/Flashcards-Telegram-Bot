package kz.karzhas.services;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.usecases.GetAllFlashcardsUseCase;
import kz.karzhas.mappers.FlashcardDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllFlashcardsServiceImpl implements GetAllFlashcardsService{

    GetAllFlashcardsUseCase getAllFlashcardsUseCase;
    FlashcardDtoMapper mapper;

    @Autowired
    public GetAllFlashcardsServiceImpl(GetAllFlashcardsUseCase getAllFlashcardsUseCase, FlashcardDtoMapper mapper) {
        this.getAllFlashcardsUseCase = getAllFlashcardsUseCase;
        this.mapper = mapper;
    }

    @Override
    public Single<List<FlashcardDto>> getAllFlashcards() {
        return getAllFlashcardsUseCase.execute()
                .map(entities -> mapper.entitiesToDtos(entities));


    }
}
