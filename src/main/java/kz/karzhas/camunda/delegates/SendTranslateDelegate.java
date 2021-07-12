package kz.karzhas.camunda.delegates;

import kz.karzhas.services.FindFlashcardByGivenRandomSideService;
import kz.karzhas.telegram_bot.BotCommands;
import kz.karzhas.telegram_bot.MessageConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendTranslateDelegate implements JavaDelegate {

    BotCommands botCommands;
    FindFlashcardByGivenRandomSideService service;

    @Autowired
    public SendTranslateDelegate(BotCommands botCommands, FindFlashcardByGivenRandomSideService service) {
        this.botCommands = botCommands;
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String randomSide = (String) delegateExecution.getVariable("randomSide");
        long chatId = Long.parseLong((String) delegateExecution.getVariable("chatId"));
        StringBuilder translationBuilder = new StringBuilder();
        service.findFlashcardByGivenRandomSide(randomSide).subscribe(dto -> {
            translationBuilder.append( dto.getBackside().equals(randomSide) ? dto.getFrontside() : dto.getBackside() );
        });
        String translation = translationBuilder.toString();
        botCommands.sendMessage(chatId, "перевод: " + "*" + translation.toUpperCase() + "*");
    }
}
