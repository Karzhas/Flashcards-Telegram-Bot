package kz.karzhas.services;

import io.reactivex.Single;
import kz.karzhas.data.dto.FlashcardDto;

public interface FindFlashcardByGivenRandomSideService {
    Single<FlashcardDto> findFlashcardByGivenRandomSide(String randomSide);
}
