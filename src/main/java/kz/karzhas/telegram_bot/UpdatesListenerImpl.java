package kz.karzhas.telegram_bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import io.reactivex.schedulers.Schedulers;
import kz.karzhas.camunda.CamundaRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatesListenerImpl implements UpdatesListener {


    private CamundaRest camundaRest;
    private BotCommandsImpl botCommands;

    @Autowired
    public UpdatesListenerImpl(CamundaRest camundaRest, BotCommandsImpl botCommands) {
        this.camundaRest = camundaRest;
        this.botCommands = botCommands;
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                if(update.callbackQuery() != null)
                    processCallbackQuery(update);
                else
                    processMessage(update);

            });
        }catch (Exception e){
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processMessage(Update update) {
        switch (update.message().text()) {
            case "start": // TODO: сделать инлайн через /
                startProcess(update);
                break;

        }
    }

    private void processCallbackQuery(Update update) {
        switch (update.callbackQuery().data()){
            case MessageConstants.ADD_FLASHCARD_CALLBACK_QUERY_ID:
                System.out.println("1");
                break;
            case MessageConstants.GET_ALL_FLASHCARDS_CALLBACK_QUERY_ID:
                System.out.println("2");
                break;
            case MessageConstants.START_LEARNING_FLASHCARDS_CALLBACK_QUERY_ID:
                System.out.println("3");
                break;
        }
    }

    private void startProcess(Update update) {
        int messageId = update.message().messageId();
        long chatId = update.message().chat().id();
        botCommands.sendMessage(chatId, MessageConstants.CAMUNDA_PROCESS_STARTED);
        camundaRest.startProcess(update)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                        result -> {
                            botCommands.sendMessage(chatId, MessageConstants.CAMUNDA_PROCESS_STARTED_SUCCESSFULLY);
                            botCommands.sendMessageWithButtons(chatId, MessageConstants.SELECT_OPTION, MessageConstants.MAIN_COMMANDS);
                        },
                        error -> error.getMessage() // TODO:
                );

    }

}
