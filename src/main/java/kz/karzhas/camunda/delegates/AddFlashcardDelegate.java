package kz.karzhas.camunda.delegates;

import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.services.SaveFlashcardService;
import kz.karzhas.telegram_bot.BotCommands;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddFlashcardDelegate implements JavaDelegate {



    SaveFlashcardService saveFlashcardService;
    BotCommands botCommands;

    @Autowired
    public AddFlashcardDelegate(SaveFlashcardService saveFlashcardService, BotCommands botCommands) {
        this.saveFlashcardService = saveFlashcardService;
        this.botCommands = botCommands;
    }



    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String front = (String) delegateExecution.getVariable("front");
        String back = (String) delegateExecution.getVariable("back");
        String callbackQueryId = (String) delegateExecution.getVariable("callbackQueryId");
        FlashcardDto flashcardDto = new FlashcardDto(front,back);
        saveFlashcardService.saveFlashcard(flashcardDto);
        botCommands.sendAnswerCallbackQuery(callbackQueryId, "ADDED", true);
    }
}
