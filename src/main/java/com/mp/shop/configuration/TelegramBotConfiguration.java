package com.mp.shop.configuration;

import com.mp.shop.services.TelegramSender;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {
    @PostConstruct
    public void start() {
        //Регистрируем бота
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramSender());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
