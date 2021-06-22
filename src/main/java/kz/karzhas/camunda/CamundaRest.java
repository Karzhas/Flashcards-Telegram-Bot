package kz.karzhas.camunda;

import com.pengrad.telegrambot.model.Update;
import io.reactivex.Single;

import java.util.concurrent.CompletableFuture;

public interface CamundaRest {
    Single<String> startProcess(Update update);

    Single<String> test(Update update);
}
