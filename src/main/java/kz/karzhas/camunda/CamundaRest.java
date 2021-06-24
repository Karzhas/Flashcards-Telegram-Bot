package kz.karzhas.camunda;

import com.pengrad.telegrambot.model.Update;
import io.reactivex.Completable;
import io.reactivex.Single;
import kz.karzhas.camunda.model.LocalVariable;
import kz.karzhas.camunda.model.Task;
import kz.karzhas.camunda.model.Variables;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public interface CamundaRest {
    Single<String> startProcess();

    Single<Task> getCurrentTask();

    void completeTask(String id);

    Completable completeTaskCompletable(String id, Variables variables);

    void completeTask(String id, Variables variables);

    void putLocalTaskVariable(String id, String varName, LocalVariable localVariable);

    Completable putLocalTaskVariableCompletable(String id, String varName, LocalVariable localVariable);

}
