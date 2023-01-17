package ru.geekbrains.telegram.bot.init;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.geekbrains.telegram.bot.enums.Menu;

public interface KeyboardInitial {


    void button(Menu menu);

    ReplyKeyboardMarkup getRkm();
}
