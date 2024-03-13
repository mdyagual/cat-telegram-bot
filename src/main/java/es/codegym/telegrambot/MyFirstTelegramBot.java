package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static es.codegym.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "cat_marathon_bot";
    public static final String TOKEN = "7019493472:AAEoyS2MaIc_pk976R9GpTuI779yMszduZ4";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        // TODO: escribiremos la funcionalidad principal del bot aquí
        startGame(getMessageText());

    }

    public void startGame(String text){
        if(text.toLowerCase().equals("/start")){
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Hackear nevera","step_1_btn"));
        }else if(text.toLowerCase().contains("descansa")){
            sendTextMessageAsync("Gracias Karen~");

        }else{
            System.out.println(text);
            doAction(getCallbackQueryButtonKey());
        }


    }

    public void doAction(String step){
        switch (step){
            case "step_1_btn":
                setUserGlory(20);
                sendTextMessageAsync(STEP_2_TEXT, Map.of("¡Tomar una salchicha! +20 ptos","step_2_btn",
                        "¡Tomar un pescadito! +20 ptos","step_2_btn" ,
                        "¡Tirar una lata de pepinillos! +20 ptos","step_2_btn"));
                break;

            case "step_2_btn":
                setUserGlory(20);
                sendTextMessageAsync(STEP_3_TEXT, Map.of("¡Hackear/destruir al robot aspirador!","step_3_btn"));
                break;

            case "step_3_btn":
                setUserGlory(70);
                sendTextMessageAsync(STEP_4_TEXT, Map.of("Enviar al robot aspirador por comida. +30 ptos","step_4_btn",
                        "Pasear en el robot aspirador +30 ptos","step_4_btn" ,
                        "¡Huir del robot aspirador! +20 ptos","step_4_btn"));
                break;

            case "step_4_btn":
                //setUserGlory(30);
                sendTextMessageAsync(STEP_5_TEXT, Map.of("Colocar y encender la GoPro","step_5_btn"));
                break;

            case "step_5_btn":
                setUserGlory(40);
                sendTextMessageAsync(STEP_6_TEXT, Map.of("¡Hora loca!","step_6_btn",
                        "Molestar al perro","step_6_btn",
                        "Cazar insectos","step_6_btn"));
                break;
            case "step_6_btn":
                //setUserGlory(40);
                sendTextMessageAsync(STEP_7_TEXT, Map.of("Hackear computadora de la Karen","step_7_btn"));
                break;
            case "step_7_btn":
                setUserGlory(50);
                sendTextMessageAsync(FINAL_TEXT);
                break;

        }


    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}