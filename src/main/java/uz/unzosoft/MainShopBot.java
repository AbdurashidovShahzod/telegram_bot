package uz.unzosoft;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MainShopBot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    System.out.println("Botga habar keldi...");
                    SendMessage sendMessage = new SendMessage();
                    String s = "Ассолому алайкум сизга кандай йордам бера оламиз?\nЗдравствуйте , чем мы можем вам помочь?";
                    sendMessage.setText(s);
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setSelective(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow = new KeyboardRow();
                    KeyboardButton keyboardButton = new KeyboardButton();
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    KeyboardButton keyboardButton3 = new KeyboardButton();

                    keyboardButton2.setRequestContact(true);
                    keyboardButton3.setRequestLocation(true);
                    keyboardButton.setText("Тилни танланг");
                    keyboardButton2.setText("Контакт юбориш");
                    keyboardButton3.setText("Турган жойимизни юбориш");

                    keyboardRow.add(keyboardButton);
                    keyboardRow.add(keyboardButton2);
                    keyboardRow.add(keyboardButton3);

                    keyboardRowList.add(keyboardRow);

                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (text.equals("Тилни танланг")) {
                    SendMessage sendMessage = new SendMessage()
                            .setText("Хуш келибсиз")
                            .setParseMode(ParseMode.MARKDOWN)
                            .setChatId(message.getChatId());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (message.hasContact()) {
                Contact contact = message.getContact();
                SendMessage sendMessage = new SendMessage()
                        .setText("Сизнинг номерингиз:+" + contact.getPhoneNumber())
                        .setChatId(message.getChatId())
                        .setParseMode(ParseMode.MARKDOWN);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (message.hasLocation()) {
                Location location = message.getLocation();
                SendMessage sendMessage = new SendMessage()
                        .setText("Сизнинг турган жойингиз:" + location.getLatitude() + ", " + location.getLongitude())
                        .setParseMode(ParseMode.MARKDOWN)
                        .setChatId(message.getChatId());
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
