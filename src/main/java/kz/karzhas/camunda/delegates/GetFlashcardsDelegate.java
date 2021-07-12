package kz.karzhas.camunda.delegates;

import io.reactivex.schedulers.Schedulers;
import kz.karzhas.services.GetAllFlashcardsService;
import kz.karzhas.telegram_bot.BotCommands;
import kz.karzhas.telegram_bot.MessageConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFlashcardsDelegate implements JavaDelegate {


    BotCommands bot;
    GetAllFlashcardsService getAllFlashcardsService;

    @Autowired
    public GetFlashcardsDelegate(BotCommands bot, GetAllFlashcardsService getAllFlashcardsService) {
        this.bot = bot;
        this.getAllFlashcardsService = getAllFlashcardsService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        getAllFlashcardsService.getAllFlashcards()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(dtos -> {
                    StringBuilder msg = new StringBuilder();
                    dtos.forEach(dto -> msg.append("*" + dto.getFrontside().toUpperCase() + "*")
                                           .append(" ")
                                           .append("*" + dto.getBackside().toUpperCase() + "*")
                                           .append("\n"));
                    long id = Long.parseLong((String) delegateExecution.getVariable("id"));
                    String callbackQueryId = (String) delegateExecution.getVariable("callbackQueryId");
                    bot.sendAnswerCallbackQuery(callbackQueryId, "", false);
                    bot.sendMessage(id, msg.toString());
                    bot.sendMessageWithButtons(id, MessageConstants.SELECT_OPTION, MessageConstants.MAIN_COMMANDS);
                });
    }
}
