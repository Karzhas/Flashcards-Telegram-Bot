package kz.karzhas.camunda;

import com.pengrad.telegrambot.model.Update;
import io.reactivex.Completable;
import io.reactivex.Single;
import kz.karzhas.camunda.delegates.ProcessInstance;
import kz.karzhas.camunda.model.LocalVariable;
import kz.karzhas.camunda.model.Task;
import kz.karzhas.camunda.model.Variables;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public interface CamundaRest {
    Single<String> startProcess();

    Single<Task> getCurrentTask();

    Completable completeTask(String id, Variables variables);
    Completable completeTask(String id);

    //Completable putLocalTaskVariable(String id, String varName, LocalVariable localVariable);
    Completable putLocalTaskVariable(String id, String varName, String value, String type);

    Completable stopProcess(String id);

    Single<ProcessInstance> getProcessInstance();



    //void completeTask(String id);
    //void completeTask(String id, Variables variables);
    // void putLocalTaskVariable(String id, String varName, LocalVariable localVariable);
    //Completable completeTask(String id, Variables variables);
    //  Completable putLocalTaskVariableCompletable(String id, String varName, LocalVariable localVariable);

}
