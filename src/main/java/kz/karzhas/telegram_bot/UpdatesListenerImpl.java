package kz.karzhas.telegram_bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import io.reactivex.schedulers.Schedulers;
import kz.karzhas.camunda.CamundaRest;
import kz.karzhas.camunda.model.LocalVariable;
import kz.karzhas.camunda.model.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatesListenerImpl implements UpdatesListener {


    private CamundaRest camundaRest;
    private BotCommands botCommands;

    @Autowired
    public UpdatesListenerImpl(CamundaRest camundaRest, BotCommandsImpl botCommands) {
        this.camundaRest = camundaRest;
        this.botCommands = botCommands;
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {

                if(update.callbackQuery() != null) // Если пришел callbackQuery update
                    processCallbackQuery(update);
                else if(update.message().replyToMessage() != null) // Если пользователь ответил на сообщение
                    processRepliedMessage(update, update.message().replyToMessage());
                else // пользователь отправил обычное сообщение
                    processIncommingMessage(update);

            });
        }catch (Exception e){
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processRepliedMessage(Update update, Message repliedToMessage) {
        String toWhichMessageUserReplied = repliedToMessage.text();
        switch (toWhichMessageUserReplied){
            case MessageConstants.SET_FRONTSIDE:
                camundaRest.getCurrentTask()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(
                                task -> {
                                    String frontMessage = update.message().text();
                                    camundaRest.putLocalTaskVariable(task.getId(), "front", frontMessage, "String")
                                            .subscribeOn(Schedulers.io())
                                            .subscribeOn(Schedulers.computation())
                                            .subscribe(
                                                    () -> botCommands.sendMessageForceReply(update.message().chat().id(), MessageConstants.SET_BACKSIDE)
                                            );


                                },
                                error -> error.getMessage() // TODO:
                        );
                break;
            case MessageConstants.SET_BACKSIDE:

                camundaRest.getCurrentTask()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(
                                task -> {
                                    String backsideMessage = update.message().text();
                                    String chatId = update.message().chat().id().toString();
                                    camundaRest.putLocalTaskVariable(task.getId(), "id", chatId, "String")
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(Schedulers.computation())
                                            .subscribe(() -> camundaRest.putLocalTaskVariable(task.getId(), "back", backsideMessage, "String")
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(Schedulers.computation())
                                                        .subscribe(() -> camundaRest.completeTask(task.getId()).subscribe() )


                                            );

                                },
                                error -> error.getMessage() // TODO:
                        );
                break;
        }
    }


    private void processIncommingMessage(Update update) {
        switch (update.message().text()) {
            case "start": // TODO: сделать инлайн через /
                startProcess(update);
                break;
            case "testTask":
                //botCommands.testSendMessageForceReply(update.message().chat().id());
                break;

        }
    }

    private void processCallbackQuery(Update update) {
        switch (update.callbackQuery().data()){
            case MessageConstants.ADD_FLASHCARD_CALLBACK_QUERY_ID:
                onAddFlashcardSelected(update);
                break;
            case MessageConstants.GET_ALL_FLASHCARDS_CALLBACK_QUERY_ID:
                onGetAllFlashcardSelected(update);
                break;
            case MessageConstants.START_LEARNING_FLASHCARDS_CALLBACK_QUERY_ID:
                onStartLearningFlashcardsSelected(update);
                break;
        }
    }

    private void onStartLearningFlashcardsSelected(Update update) {
        camundaRest.getCurrentTask()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                        task -> {
                            Variables v = new Variables();
                            v.addVariable(new Variables.Variable("command", "string", "startLearnFlashcards"));

                            //camundaRest.completeTask(task.getId(), v);
                        },
                        error -> error.getMessage() // TODO:
                );
    }

    private void onGetAllFlashcardSelected(Update update) {
        camundaRest.getCurrentTask()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                        task -> {
                            Variables v = new Variables();
                            v.addVariable(new Variables.Variable("command", "string", "getAllFlashcards"));
                            v.addVariable(new Variables.Variable("id", "string", update.callbackQuery().from().id().toString()));
                            v.addVariable(new Variables.Variable("callbackQueryId", "string", update.callbackQuery().id()));
                            camundaRest.completeTask(task.getId(), v)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(Schedulers.computation())
                                    .subscribe(() -> System.out.println("task completed after getAllFlashcards"));

                        },
                        error -> error.getMessage() // TODO:
                );
    }

    private void onAddFlashcardSelected(Update update) {
        camundaRest.getCurrentTask()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                        task -> {
                            camundaRest.putLocalTaskVariable(task.getId(), "callbackQueryId", update.callbackQuery().id(), "String")
                                    .subscribe(() -> {
                                        Variables v = new Variables();
                                        v.addVariable(new Variables.Variable("command", "string", "addFlashcard"));
                                        camundaRest.completeTask(task.getId(), v)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(Schedulers.computation())
                                                .subscribe(() -> {
                                                    //camundaRest.putLocalTaskVariable(task.getId(), "callbackQueryId", update.callbackQuery().id(), "String").subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe();
                                                    botCommands.sendMessageForceReply(update.callbackQuery().from().id(), MessageConstants.SET_FRONTSIDE);

                                                });

                                    });

                        },
                        error -> error.getMessage() // TODO:
                );
    }

    private void startProcess(Update update) {
        long chatId = update.message().chat().id();
        botCommands.sendMessage(chatId, MessageConstants.CAMUNDA_PROCESS_STARTED);
        camundaRest.startProcess()
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
