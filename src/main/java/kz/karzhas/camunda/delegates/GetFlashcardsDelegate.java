package kz.karzhas.camunda.delegates;

import io.reactivex.schedulers.Schedulers;
import kz.karzhas.domain.usecases.GetAllFlashcardsUseCase;
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
    GetAllFlashcardsService getAllFlashcardsUseCase;

    @Autowired
    public GetFlashcardsDelegate(BotCommands bot, GetAllFlashcardsService getAllFlashcardsUseCase) {
        this.bot = bot;
        this.getAllFlashcardsUseCase = getAllFlashcardsUseCase;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        getAllFlashcardsUseCase.getAllFlashcards()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(dtos -> {
                    StringBuilder msg = new StringBuilder();
                    dtos.forEach(dto -> msg.append(dto.getFrontside())
                                           .append(" ")
                                           .append(dto.getBackside())
                                           .append("\n"));
                    long id = Long.parseLong((String) delegateExecution.getVariable("id"));
                    String callbackQueryId = (String) delegateExecution.getVariable("callbackQueryId");
                    bot.sendAnswerCallbackQuery(callbackQueryId, "", false);
                    bot.sendMessage(id, msg.toString());
                    bot.sendMessageWithButtons(id, MessageConstants.SELECT_OPTION, MessageConstants.MAIN_COMMANDS);
                });
    }
}
