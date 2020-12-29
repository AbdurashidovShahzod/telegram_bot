package uz.unzosoft;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MainShopBot2 extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    System.out.println("Botga habar keldi...");
                    SendMessage sendMessage = new SendMessage();
                    String s = "Ассолому алайкум манзилни тангланг?\nЗдравствуйте ,выбрайте ссылка?";
                    sendMessage.setText(s);
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(message.getChatId());

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
                    /**
                     * Bu yerda horizantal holatda chiqarish uchun bitta inlineKeyboardButtonList
                     * dan foydalanish kerak.
                     * Agar vertical holatda bo'lsa bir nechta inlineKeyboardButtonList
                     * dan foydalanish kerak
                     */
                    List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                    List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();
                    List<InlineKeyboardButton> inlineKeyboardButtonList3 = new ArrayList<>();

                    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                    InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
                    inlineKeyboardButton.setText("My first telegram account");
                    inlineKeyboardButton2.setText("My Git Hub account");
                    inlineKeyboardButton3.setText("My second telegram account");


                    inlineKeyboardButton.setUrl("https://t.me/studentTUIT");
                    inlineKeyboardButton2.setUrl("https://github.com/AbdurashidovShahzod");
                    inlineKeyboardButton3.setUrl("https://t.me/becausel");

                    inlineKeyboardButtonList.add(inlineKeyboardButton);
                    inlineKeyboardButtonList2.add(inlineKeyboardButton2);
                    inlineKeyboardButtonList3.add(inlineKeyboardButton3);

                    inlineButtons.add(inlineKeyboardButtonList);
                    inlineButtons.add(inlineKeyboardButtonList2);
                    inlineButtons.add(inlineKeyboardButtonList3);

                    inlineKeyboardMarkup.setKeyboard(inlineButtons);
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
            } else if (update.hasCallbackQuery()) {
                Message message1 = update.getCallbackQuery().getMessage();
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData();
                SendMessage sendMessage = new SendMessage()
                        .setParseMode(ParseMode.MARKDOWN)
                        .setChatId(message1.getChatId());

                switch (data) {
                    case "uzb":
                        sendMessage.setText("O'zbek tili...");
                        break;
                    case "rus":
                        sendMessage.setText("Русский язык...");
                        break;
                    case "eng":
                        sendMessage.setText("English language...");
                        break;
                }
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getBotUsername() {
        return "";
    }

    public String getBotToken() {
        return "";
    }
}
