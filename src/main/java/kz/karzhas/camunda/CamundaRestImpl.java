package kz.karzhas.camunda;

import com.pengrad.telegrambot.model.Update;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import kz.karzhas.camunda.model.LocalVariable;
import kz.karzhas.camunda.model.Task;
import kz.karzhas.camunda.model.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CamundaRestImpl implements CamundaRest{


    @Value("${bot.baseurl}")
    private String baseurl;

    @Autowired
    private RestTemplate restTemplate;

    public CamundaRestImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Single<String> startProcess() {
        String endpoint = "process-definition/key/flashcards/start";

        return Single.create(singleEmitter -> {
            ResponseEntity<String> response = null;
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap<String, String>();
                map.put("Content-Type", "application/json");
                headers.setAll(map);
                HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>("{}", headers);
                String result = restTemplate.exchange(baseurl + endpoint, HttpMethod.POST, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
                }).getBody();

                // TODO : добавить что если в теле респонса пришла ошибка то отправить onError

                singleEmitter.onSuccess(result);

            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        });
    }

    @Override
    public Single<Task> getCurrentTask() {
        String endpoint = "task?processDefinitionKey=flashcards";

        return Single.create(singleEmitter -> {
            ResponseEntity<List<Task>> response = null;
            try {

                response = restTemplate.exchange(
                        baseurl + endpoint,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Task>>() {}

                );

                Task task = response.getBody().get(0);
                // TODO : добавить что если в теле респонса пришла ошибка то отправить onError

                singleEmitter.onSuccess(task);

            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        });
    }

    @Override
    public void completeTask(String id) {
        String endpoint = "task/" + id + "/complete";

        Single.create(singleEmitter -> {
            ResponseEntity<String> response = null;
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap<String, String>();
                map.put("Content-Type", "application/json");
                headers.setAll(map);
                HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>("{}", headers);
                String result = restTemplate.exchange(baseurl + endpoint, HttpMethod.POST, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
                }).getBody();

            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        })
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> System.out.println("subscribe " + result));
    }

    @Override
    public Completable completeTaskCompletable(String id, Variables variables) {
        String endpoint = "task/" + id + "/complete";

        return Completable.create(singleEmitter -> {
            ResponseEntity<String> response = null;
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap<String, String>();
                map.put("Content-Type", "application/json");
                headers.setAll(map);
                HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>(variables.toString(), headers);
                String result = restTemplate.exchange(baseurl + endpoint, HttpMethod.POST, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
                }).getBody();
                singleEmitter.onComplete();

            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        });
//                .observeOn(Schedulers.computation())
//                .subscribeOn(Schedulers.io())
//                .subscribe(result -> System.out.println("subscribe " + result));

    }


    @Override
    public void completeTask(String id, Variables variables) {
        String endpoint = "task/" + id + "/complete";

        Single.create(singleEmitter -> {
            ResponseEntity<String> response = null;
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap<String, String>();
                map.put("Content-Type", "application/json");
                headers.setAll(map);
                System.out.println(variables.toString());
                HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>(variables.toString(), headers);
                String result = restTemplate.exchange(baseurl + endpoint, HttpMethod.POST, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
                }).getBody();

            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        })
          .observeOn(Schedulers.computation())
          .subscribeOn(Schedulers.io())
          .subscribe(result -> System.out.println("subscribe " + result));
    }

    @Override
    public void putLocalTaskVariable(String id, String varName, LocalVariable localVariable) {


        String endpoint = "task/" + id + "/variables/" + varName;

        Single.create(singleEmitter -> {
            ResponseEntity<String> response = null;
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap<String, String>();
                map.put("Content-Type", "application/json");
                headers.setAll(map);
               // System.out.println(variables.toString());
                HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>(localVariable.toString(), headers);
                ResponseEntity result = restTemplate.exchange(baseurl + endpoint, HttpMethod.PUT, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
                });

            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        })
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> System.out.println("subscribe " + result));
    }

    @Override
    public Completable putLocalTaskVariableCompletable(String id, String varName, LocalVariable localVariable) {
        String endpoint = "task/" + id + "/variables/" + varName;

        return Completable.create(singleEmitter -> {
            ResponseEntity<String> response = null;
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                Map map = new HashMap<String, String>();
                map.put("Content-Type", "application/json");
                headers.setAll(map);
                // System.out.println(variables.toString());
                HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>(localVariable.toString(), headers);
                ResponseEntity result = restTemplate.exchange(baseurl + endpoint, HttpMethod.PUT, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
                });

                singleEmitter.onComplete();



            } catch (Exception e) {
                singleEmitter.onError(e);
            }
        });

    }


}
