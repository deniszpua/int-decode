package com.github.deniszpua.itndecode.decoder;

import java.util.Calendar;

/**
 * Created by deniszpua on 09.04.15.
 */
public interface ITNDecode {
    public static int UNKNOWN = -1;
    public enum Sex {MALE, FEMALE}

    /**
     *
     * @return true, if string,used while creating object instance,
     *         represents valid individual tax number
     */
    public boolean isValid();

    /**
     *
     * @return Calendar object containing birthday data
     */
    public Calendar getBirthday();


    public Sex getSex();

    /**
     *
     * @return int in range from 0 to 9, that calculated from first nine symbols
     */
    public int getControlDigitValue();
}
