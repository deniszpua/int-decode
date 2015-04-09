package com.github.deniszpua.itndecode.decoder;

import java.util.Calendar;

/**
 * Created by deniszpua on 09.04.15.
 */
public class ITNDecoder implements ITNDecode {

    public ITNDecoder(String string) {
    }

    public boolean isValid() {
        return false;
    }

    public Calendar getBirthday() {
        return null;
    }

    public Sex getSex() {
        return null;
    }

    public int getControlDigitValue() {
        return 0;
    }
}
