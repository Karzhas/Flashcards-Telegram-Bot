package kz.karzhas;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import kz.karzhas.telegram_bot.UpdatesListenerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Application {

  @Autowired
  UpdatesListenerImpl updatesListener;
  String token = "1846265656:AAG5Ag6rj7ErfHGnem-yDqlQEvcYKvEiIZU";


  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  private void botInit(){
    TelegramBot bot = new TelegramBot(token);
    bot.setUpdatesListener(updatesListener);
  }

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }


}