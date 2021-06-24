package kz.karzhas.services;

import io.reactivex.Single;
import kz.karzhas.data.dto.FlashcardDto;

import java.util.List;

public interface GetAllFlashcardsService {
    Single<List<FlashcardDto>> getAllFlashcards();
}
