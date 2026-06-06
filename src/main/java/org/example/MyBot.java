import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "kinolar_uz_rubot";
    }

    @Override
    public String getBotToken() {
        return "8367432620:AAGyEErCwX_reS8yQxGhlEYfOWIMI50Itf8";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (text.equals("/start")) {
                sendMessage(chatId,
                        "Assalomu alaykum!\n\nQanday referat kerak?\n\n3 yuboring.");
            }

            else if (text.equals("3")) {
                sendDocument(chatId, "src/main/resources/referat3.docx");
            }

            else {
                sendMessage(chatId,
                        "Faqat 3 raqamini yuboring.");
            }
        }
    }

    // 📩 TEXT YUBORISH
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // 📄 FILE (WORD/PDF) YUBORISH
    private void sendDocument(long chatId, String filePath) {
        SendDocument document = new SendDocument();
        document.setChatId(String.valueOf(chatId));
        document.setDocument(new InputFile(new File(filePath)));

        try {
            execute(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}