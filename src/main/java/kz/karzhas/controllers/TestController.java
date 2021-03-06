package kz.karzhas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import kz.karzhas.camunda.CamundaRest;
import kz.karzhas.data.dto.FlashcardDto;
import kz.karzhas.domain.entity.Flashcard;
import kz.karzhas.domain.usecases.AddNewFlashcardUseCase;
import kz.karzhas.domain.usecases.GetAllFlashcardsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {


    @Autowired
    CamundaRest camundaRest;

    AddNewFlashcardUseCase addNewFlashcardUseCase;
    GetAllFlashcardsUseCase getAllFlashcardsUseCase;

    @Autowired
    public TestController(AddNewFlashcardUseCase addNewFlashcardUseCase, GetAllFlashcardsUseCase getAllFlashcardsUseCase) {
        this.addNewFlashcardUseCase = addNewFlashcardUseCase;
        this.getAllFlashcardsUseCase = getAllFlashcardsUseCase;
    }




    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/test")
    public void test(){
        getAllFlashcardsUseCase.execute().subscribe(flashcards -> {
            String json =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(flashcards);
            List<Flashcard> f = Arrays.asList(objectMapper.readValue(json, Flashcard[].class));
            System.out.println(1);
        });
    }

    @GetMapping("/add/{front}/{back}")
    public void addFlashcard(@PathVariable String front, @PathVariable String back){
        Flashcard flashcard = new Flashcard(front,back, 0);
        addNewFlashcardUseCase.execute(flashcard).subscribe(() -> System.out.println("Added new flashcard " + front + " " + back));
    }

    @GetMapping("/get")
    public void getFlashcards(){
        getAllFlashcardsUseCase.execute().subscribe(flashcards -> {
            for(Flashcard f : flashcards){
                System.out.println(f.getFrontside() + " " + f.getBackside());
            }
        });
    }
}
