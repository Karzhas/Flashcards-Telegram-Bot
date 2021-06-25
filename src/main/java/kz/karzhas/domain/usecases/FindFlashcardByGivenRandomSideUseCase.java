package kz.karzhas.domain.usecases;

import io.reactivex.Single;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.repositories.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindFlashcardByGivenRandomSideUseCase implements BaseUseCaseWithParam<String, Single<Flashcard>> {

    @Autowired
    FlashcardRepository flashcardRepository;

    public FindFlashcardByGivenRandomSideUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }


    @Override
    public Single<Flashcard> execute(String randomSide) {
        return Single.create(singleEmitter -> {

           flashcardRepository.getFlashcards().subscribe(flashcards -> {
              for(Flashcard card : flashcards){
                  if(card.getBackside().equals(randomSide)){
                      singleEmitter.onSuccess(card);
                      return;
                  }
              }
               for(Flashcard card : flashcards){
                   if(card.getFrontside().equals(randomSide)){
                       singleEmitter.onSuccess(card);
                       return;
                   }
               }

           });
        });


    }
}
