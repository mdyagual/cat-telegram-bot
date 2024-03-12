package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "cat_marathon_bot";
    public static final String TOKEN = "7019493472:AAEoyS2MaIc_pk976R9GpTuI779yMszduZ4";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        // TODO: escribiremos la funcionalidad principal del bot aquí
        if(getMessageText().toLowerCase().contains("me llamo") || getMessageText().toLowerCase().contains("mi nombre es")){
            sendTextMessageAsync("Encantado de conocerte, soy *Gato*");
        }else if(getMessageText().equals("/start")){
            sendTextMessageAsync("Hola _futuro_ *programador*!");
        }else{
            sendTextMessageAsync("Hola, cuál es tu nombre?");
        }


    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}