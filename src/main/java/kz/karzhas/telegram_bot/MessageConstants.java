package kz.karzhas.telegram_bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageConstants {
    public static final String SET_FRONTSIDE = "Введите слово на русском языке";
    public static final String SET_BACKSIDE = "Введите слово на английском языке";
    public final static String CAMUNDA_PROCESS_STARTED = "Процесс CAMUNDA BPM запускается... пожалуйста, подождите";

    public final static String CAMUNDA_PROCESS_STARTED_SUCCESSFULLY = "Процесс успешно создан";

    public final static String SELECT_OPTION = "Выберите команду";

    public final static String WRONG_CALLBACK_QUERY_ID = "wrongAnswer";
    public final static String TRANSLATE_CALLBACK_QUERY_ID = "showTranslation";
    public final static String CORRECT_CALLBACK_QUERY_ID = "correctAnswer";
    //private static final String START_LEARN_CALLBACK_QUERY_ID = "startLearn";

    public final static List<Button> MAIN_COMMANDS = new ArrayList<Button>(Arrays.asList(
            new Button("Добавить", MessageConstants.ADD_FLASHCARD_CALLBACK_QUERY_ID),
            new Button("Показать все", MessageConstants.GET_ALL_FLASHCARDS_CALLBACK_QUERY_ID),
            new Button("Учить", MessageConstants.START_LEARNING_FLASHCARDS_CALLBACK_QUERY_ID)
           // new Button("Закончить процесс", MessageConstants.END_PROCESS_CALLBACK_QUERY_ID)

    ));

    public final static List<Button> OPTIONS_TO_ANSWER_ON_FLASHCARD = new ArrayList<Button>(Arrays.asList(
            new Button("-", MessageConstants.WRONG_CALLBACK_QUERY_ID),
            new Button("?", MessageConstants.TRANSLATE_CALLBACK_QUERY_ID),
            new Button("+", MessageConstants.CORRECT_CALLBACK_QUERY_ID),
            new Button("НАЗАД", MessageConstants.EXIT_CALLBACK_QUERY_ID)
            // new Button("Закончить процесс", MessageConstants.END_PROCESS_CALLBACK_QUERY_ID)

    ));




    public final static String END_PROCESS_CALLBACK_QUERY_ID = "endProcess";

    public final static String EXIT_CALLBACK_QUERY_ID = "exitCallbackData";


    public final static String ADD_FLASHCARD_CALLBACK_QUERY_ID = "addFlashcardCallbackData";

    public final static String GET_ALL_FLASHCARDS_CALLBACK_QUERY_ID = "getAllFlashcardsCallbackData";

    public final static String START_LEARNING_FLASHCARDS_CALLBACK_QUERY_ID = "startLearningFlashcardsCallbackData";

    public static final String PROCESS_INSTANCE = "flashcards";
}
