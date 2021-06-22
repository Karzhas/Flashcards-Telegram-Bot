package kz.karzhas.camunda;

import com.pengrad.telegrambot.model.Update;
import io.reactivex.Observable;
import io.reactivex.Single;
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

import java.util.HashMap;
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
    public Single<String> startProcess(Update update) {
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
    public Single<String> test(Update update) {
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




}
