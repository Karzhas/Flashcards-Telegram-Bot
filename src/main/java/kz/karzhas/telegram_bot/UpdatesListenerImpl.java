package kz.karzhas.telegram_bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UpdatesListenerImpl implements UpdatesListener {

    String baseurl = "http://localhost:8080/engine-rest/";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public int process(List<Update> updates) {
        //updates.forEach(update -> System.out.println(update.message().text()));
        updates.forEach(update -> {
            switch (update.message().text()){
                case "start": start(); break;

            }
        });
        // ... process updates
        // return id of last processed update or confirm them all
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void start() {
        String url = baseurl + "process-definition/key/flashcards/start";

        ResponseEntity<String> response = null;
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            Map map = new HashMap<String, String>();
            map.put("Content-Type", "application/json");
            headers.setAll(map);
            HttpEntity<?> _HttpEntityRequestBodyJson = new HttpEntity<>("{}", headers);
            response = restTemplate.exchange(url, HttpMethod.POST, _HttpEntityRequestBodyJson, new ParameterizedTypeReference<String>() {
            });
            System.out.println(response);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("wtf");
        //


        //System.out.println(result);

    }

}
