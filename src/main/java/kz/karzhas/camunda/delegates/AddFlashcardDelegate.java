package kz.karzhas.camunda.delegates;

import io.reactivex.schedulers.Schedulers;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.usecases.AddNewFlashcardUseCase;
import kz.karzhas.services.AddFlashcardService;
import kz.karzhas.telegram_bot.BotCommands;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AddFlashcardDelegate implements JavaDelegate {



    AddFlashcardService addFlashcardService;
    BotCommands botCommands;

    @Autowired
    public AddFlashcardDelegate(AddFlashcardService addFlashcardService, BotCommands botCommands) {
        this.addFlashcardService = addFlashcardService;
        this.botCommands = botCommands;
    }



    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String front = (String) delegateExecution.getVariable("front");
        String back = (String) delegateExecution.getVariable("back");
        String callbackQueryId = (String) delegateExecution.getVariable("callbackQueryId");
        FlashcardDto flashcardDto = new FlashcardDto(front,back);
        addFlashcardService.addFlashcard(flashcardDto);
        botCommands.sendAnswerCallbackQuery(callbackQueryId, "ADDED", true);
    }
}
