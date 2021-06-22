package kz.karzhas.telegram_bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotCommandsImpl implements BotCommands{

    TelegramBot bot;


    @Autowired
    public BotCommandsImpl(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(long id, String message) {
        SendMessage sendMessage = new SendMessage(id, message);
        //


        //
        SendResponse response = bot.execute(sendMessage);

    }

    @Override
    public void sendMessageWithButtons(long id, String message, List<Button> buttons) {
        SendMessage sendMessage = new SendMessage(id, message);
        InlineKeyboardButton[] keyboardButtons = new InlineKeyboardButton[buttons.size()];
        keyboardButtons = buttons
                .stream()
                .map(btn -> new InlineKeyboardButton(btn.getText()).callbackData(btn.getCallback_data()))
                .collect(Collectors.toList())
                .toArray(keyboardButtons);

//        InlineKeyboardButton[] keyboardButtons = (InlineKeyboardButton[]) buttons.toArray();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboardButtons);
        sendMessage.replyMarkup(inlineKeyboardMarkup);
        bot.execute(sendMessage);
    }

    @Override
    public void testInlineKeyboard(long id) {
        SendMessage sendMessage = new SendMessage(id, "");
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Callback 1").callbackData("callback_1"),
                new InlineKeyboardButton("Switch!").switchInlineQuery("switch_inline_query"));


        sendMessage.replyMarkup(inlineKeyboard);
        SendResponse response = bot.execute(sendMessage);

    }

    @Override
    public void testReplyKeyboard(long id) {
        SendMessage sendMessage = new SendMessage(id, "");
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"first row button1", "first row button2"},
                new String[]{"second row button1", "second row button2"})
                .oneTimeKeyboard(true)   // optional
                .resizeKeyboard(true)    // optional
                .selective(true);        // optional
        sendMessage.replyMarkup(replyKeyboardMarkup);
        SendResponse response = bot.execute(sendMessage);

    }

    @Override
    public void testForceReply(long id) {
        SendMessage sendMessage = new SendMessage(id, "");
    }
}
