package kz.karzhas.camunda.delegates;

import io.reactivex.schedulers.Schedulers;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.usecases.AddNewFlashcardUseCase;
import kz.karzhas.services.AddFlashcardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AddFlashcardDelegate implements JavaDelegate {



    AddFlashcardService addFlashcardService;

    @Autowired
    public AddFlashcardDelegate(AddFlashcardService addFlashcardService) {
        this.addFlashcardService = addFlashcardService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String front = (String) delegateExecution.getVariable("front");
        String back = (String) delegateExecution.getVariable("back");
        FlashcardDto flashcardDto = new FlashcardDto(front,back);
        addFlashcardService.addFlashcard(flashcardDto);

    }
}
