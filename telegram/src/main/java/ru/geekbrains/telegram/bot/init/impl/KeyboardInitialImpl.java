package ru.geekbrains.telegram.bot.init.impl;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.geekbrains.telegram.bot.enums.Commands;
import ru.geekbrains.telegram.bot.enums.Menu;
import ru.geekbrains.telegram.bot.init.KeyboardInitial;

import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.telegram.bot.enums.Commands.*;


public class KeyboardInitialImpl implements KeyboardInitial {
    private ReplyKeyboardMarkup rkm;
    private final List<KeyboardRow> keyboardRowList;

    public KeyboardInitialImpl() {
        this.rkm = new ReplyKeyboardMarkup();
        this.keyboardRowList = new ArrayList<>();
    }

    @Override
    public void button(Menu menu) {
        KeyboardRow keyboardRow = new KeyboardRow();
        switch (menu) {
            case MAIN ->
                changeKeyboard(keyboardRow, MEM, HELP, TRANSFER);

            case MEMS ->
                changeKeyboard(keyboardRow, BACK, NEXT, MENU);

        }
    }

    private void changeKeyboard(KeyboardRow keyboardRow, Commands back, Commands next, Commands menu) {
        keyboardRow.add(new KeyboardButton(back.name()));
        keyboardRow.add(new KeyboardButton(next.name()));
        keyboardRow.add(new KeyboardButton(menu.name()));
        keyboardRowList.add(keyboardRow);
    }

    @Override
        public ReplyKeyboardMarkup getRkm () {
            rkm = new ReplyKeyboardMarkup();
            rkm.setSelective(true);
            rkm.setResizeKeyboard(true);
            rkm.setOneTimeKeyboard(false);
            rkm.setKeyboard(keyboardRowList);
            return rkm;
        }
}