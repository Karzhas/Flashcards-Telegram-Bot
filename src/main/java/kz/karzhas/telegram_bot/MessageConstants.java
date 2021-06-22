package kz.karzhas.telegram_bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageConstants {
    final static String CAMUNDA_PROCESS_STARTED = "Процесс CAMUNDA BPM запускается... пожалуйста, подождите";

    final static String CAMUNDA_PROCESS_STARTED_SUCCESSFULLY = "Процесс успешно создан";

    final static String SELECT_OPTION = "Выберите команду";

    final static List<Button> MAIN_COMMANDS = new ArrayList<Button>(Arrays.asList(
            new Button("Добавить флэшкарточку", MessageConstants.ADD_FLASHCARD_CALLBACK_QUERY_ID),
            new Button("Показать все флэшкарты", MessageConstants.GET_ALL_FLASHCARDS_CALLBACK_QUERY_ID)

    ));



    final static String ADD_FLASHCARD_CALLBACK_QUERY_ID = "addFlashcardCallbackData";

    final static String GET_ALL_FLASHCARDS_CALLBACK_QUERY_ID = "getAllFlashcardsCallbackData";

    final static String START_LEARNING_FLASHCARDS_CALLBACK_QUERY_ID = "startLearningFlashcardsCallbackData";

}
