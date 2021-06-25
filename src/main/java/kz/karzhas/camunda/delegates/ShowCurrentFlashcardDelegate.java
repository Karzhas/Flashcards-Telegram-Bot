package kz.karzhas.camunda.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.services.GetAllFlashcardsService;
import kz.karzhas.telegram_bot.BotCommands;
import kz.karzhas.telegram_bot.MessageConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class ShowCurrentFlashcardDelegate implements JavaDelegate {

    BotCommands botCommands;
    GetAllFlashcardsService getAllFlashcardsService;
    ObjectMapper objectMapper;

    @Autowired
    public ShowCurrentFlashcardDelegate(BotCommands botCommands, GetAllFlashcardsService getAllFlashcardsService, ObjectMapper objectMapper) {
        this.botCommands = botCommands;
        this.getAllFlashcardsService = getAllFlashcardsService;
        this.objectMapper = objectMapper;
    }

    List<FlashcardDto> flashcardDtos;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int currentIndex = Integer.parseInt((String)delegateExecution.getVariable("currentIndex"));
        long chatId = Long.parseLong((String) delegateExecution.getVariable("chatId"));
        //botCommands.sendMessageWithButtons(chatId, "", MessageConstants.OPTIONS_TO_ANSWER_ON_FLASHCARD);
        flashcardDtos = new ArrayList<>();
        if(delegateExecution.getVariable("flashcards") == null || ((String)(delegateExecution.getVariable("flashcards"))).isEmpty()){
            getAllFlashcardsService.getAllFlashcards().subscribe(dtos -> {
                flashcardDtos = dtos;
                String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(flashcardDtos);
                delegateExecution.setVariable("flashcards", json);
            });
        }else{
            String cache = (String) delegateExecution.getVariable("flashcards");
            flashcardDtos = Arrays.asList(objectMapper.readValue(cache, FlashcardDto[].class));
        }
        String randomSide = new Random().nextBoolean() ? flashcardDtos.get(currentIndex).getFrontside() : flashcardDtos.get(currentIndex).getBackside();
        botCommands.sendMessageWithButtons(chatId, randomSide, MessageConstants.OPTIONS_TO_ANSWER_ON_FLASHCARD);
        currentIndex++;
        if(currentIndex == flashcardDtos.size())
            currentIndex = 0;
    }
}
