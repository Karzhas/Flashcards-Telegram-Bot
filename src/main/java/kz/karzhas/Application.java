package kz.karzhas;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import kz.karzhas.telegram_bot.UpdatesListenerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableAsync
public class Application {

  UpdatesListenerImpl updatesListener;
  TelegramBot bot;
  @Value("${bot.token}")
  String token;

  @Autowired
  public void setUpdatesListener(UpdatesListenerImpl updatesListener) {
    this.updatesListener = updatesListener;
  }

  @Autowired
  public void setBot(TelegramBot bot) {
    this.bot = bot;
  }

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  private void botInit(){
    bot.setUpdatesListener(updatesListener);
  }

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public TelegramBot getTelegramBot(){
    return new TelegramBot(token);
  }

}