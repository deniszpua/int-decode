package com.github.deniszpua.itndecode.decoder;

import java.util.Calendar;

/**
 * Created by deniszpua on 09.04.15.
 * Simple class to extract data from Ukrainian individual tax
 * number string representation. Not throws errors or give
 * any messages, if its instance is initialized from not valid
 * itn string. In this case, getBirthday and getSex instance
 * methods would return null values. In case, if exception
 * behaviour is needed, class should be extended with use of
 * it isValid method.
 */
public class ITNDecoder implements ITNDecode {

    //Constants, from which birthday counts
    private static final int START_YEAR = 1900;
    private static final int START_MONTH = 0;
    private static final int START_DAY = 1;

    //predefined digits in itn
    private static final int CONTROL_DIGIT_POSITION = 9;
    private static final int SEX_DIGIT_POSITION = 8;
    public static final int BIRTHDAY_LAST_POSITION = 5;

    private final int[] digits;
    private boolean isValidNumber;
    private int controlDigit;
    private final Calendar birthday;
    private final Sex sex;

    public ITNDecoder(String string) {
        this.digits = toIntegerArray(string);
        isValidNumber = validString(string);

        if (isValidNumber) {
            controlDigit = computeControlDigit(string);

            //Even ninth digit for male and odd for females
            sex = ((digits[SEX_DIGIT_POSITION] % 2 == 0) ? Sex.FEMALE: Sex.MALE);
            //First five digits is birthday offset from 01.01.1900 in days
            int birthdayOffset = Integer.parseInt(string.substring(0, BIRTHDAY_LAST_POSITION));
            birthday = Calendar.getInstance();
            birthday.clear();
            birthday.set(START_YEAR, START_MONTH, START_DAY);
            birthday.add(Calendar.DAY_OF_MONTH, birthdayOffset - 1);
        }
        else {
            controlDigit = (isTenDigitNumber(string) ? computeControlDigit(string) : -1);
            sex = null;
            birthday = null;
        }

    }

    public boolean isValid() {
        return isValidNumber;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public int getControlDigitValue() {
        return controlDigit;
    }

    protected static boolean validString(String string) {
        //string format verification
        if (!isTenDigitNumber(string)) return false;

        //control digit verification
        int controlDigit = computeControlDigit(string);
        return controlDigit ==
                Character.getNumericValue(string.toCharArray()[CONTROL_DIGIT_POSITION]);
    }

    public static final int NUMBER_OF_DIGITS = 10;

    private static boolean isTenDigitNumber(String string) {
        //not ten digits number
        if (string.length() != NUMBER_OF_DIGITS) return false;
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static int[] toIntegerArray(String string) {
        int[] digits = new int[string.length()];
        for (int i = 0; i < string.length(); i++) {
            digits[i] = Character.getNumericValue(string.toCharArray()[i]);
        }
        return digits;
    }

    private static final int[] VALIDATION_COEFFICIENTS =
            {-1, 5, 7, 9, 4, 6, 10, 5, 7};
    public static final int CONTROL_DIGIT_BASE = 11;
    public static final int CONTROL_DIGIT_DIVISOR = 10;

    private static int computeControlDigit(String string) {
        int[] digits = toIntegerArray(string);
        int sum = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS - 1; i++) {
            sum += digits[i] * VALIDATION_COEFFICIENTS[i];
        }
        return (sum / CONTROL_DIGIT_BASE) % CONTROL_DIGIT_DIVISOR;
    }
}
