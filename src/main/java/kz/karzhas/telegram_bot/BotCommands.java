package kz.karzhas.telegram_bot;

import java.util.List;

public interface BotCommands {
    void sendMessage(long id, String message);

    void sendMessageWithButtons(long id, String message, List<Button> buttons);

    void sendMessageForceReply(long id, String title);


    void testInlineKeyboard(long id);

    void testReplyKeyboard(long id);

    void testForceReply(long id);

}
