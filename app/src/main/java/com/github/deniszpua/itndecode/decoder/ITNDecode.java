package com.github.deniszpua.itndecode.decoder;

import java.util.Calendar;

/**
 * Created by deniszpua on 09.04.15.
 */
public interface ITNDecode {
    public enum Sex {MALE, FEMALE}

    public boolean isCorrectString(String string);
    public ITNDecode getInstance(String string);
    public Calendar getBirthday();
    public Sex getSex();
    public int getControlDigitValue();
}
