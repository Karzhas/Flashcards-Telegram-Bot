package kz.karzhas.camunda.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.services.FindFlashcardByGivenRandomSideService;
import kz.karzhas.services.GetAllFlashcardsService;
import kz.karzhas.services.SaveFlashcardService;
import kz.karzhas.telegram_bot.BotCommands;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class UpdateRatingDelegate implements JavaDelegate {


    GetAllFlashcardsService getAllFlashcardsService;
    SaveFlashcardService saveFlashcardService;
    ObjectMapper objectMapper;

    @Autowired
    public UpdateRatingDelegate(GetAllFlashcardsService getAllFlashcardsService, SaveFlashcardService saveFlashcardService, ObjectMapper objectMapper) {
        this.getAllFlashcardsService = getAllFlashcardsService;
        this.saveFlashcardService = saveFlashcardService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String cache = (String) delegateExecution.getVariable("flashcards");
        List<FlashcardDto> flashcardDtos = Arrays.asList(objectMapper.readValue(cache, FlashcardDto[].class));
        int size = flashcardDtos.size();
        int currentIndex = Integer.parseInt((String)delegateExecution.getVariable("currentIndex"));
        boolean isCorrect = ((String) delegateExecution.getVariable("isCorrect")).equals("true");
        int delta = isCorrect ? +1 : -1;
        FlashcardDto needToUpdate = flashcardDtos.get(currentIndex);
        needToUpdate.setRating(needToUpdate.getRating() + delta);
        saveFlashcardService.saveFlashcard(needToUpdate);
        currentIndex++;
        if(currentIndex == size)
            currentIndex = 0;
        String currentIndexStr = currentIndex + "";
        delegateExecution.setVariable("currentIndex", currentIndexStr);
    }
}
