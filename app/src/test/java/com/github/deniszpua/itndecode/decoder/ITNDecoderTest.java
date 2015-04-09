package com.github.deniszpua.itndecode.decoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;


public class ITNDecoderTest {
    private static final String MALE_ITN_STRING = "2955210055";
    private static final String FEMALE_ITN_STRING = "3039315783";
    private static final String INVALID_ITN_STRING = "1234567890";
    private static final String SHORT_ITN_STRING = "1234567";


    private ITNDecode male_code;
    private ITNDecode female_code;
    private ITNDecode invalid_code;
    private ITNDecode short_code;

    @Before
    public void setUp() throws Exception {
        male_code = new ITNDecoder(MALE_ITN_STRING);
        female_code = new ITNDecoder(FEMALE_ITN_STRING);
        invalid_code = new ITNDecoder(INVALID_ITN_STRING);
        short_code = new ITNDecoder(SHORT_ITN_STRING);
    }

    @Test
    public void testIsValid() throws Exception {
        Assert.assertEquals(true, male_code.isValid());
        Assert.assertEquals(true, female_code.isValid());
        Assert.assertEquals(false, invalid_code.isValid());
        Assert.assertEquals(false, short_code.isValid());
    }

    @Test
    public void testGetControlDigitValue() throws Exception {
        Assert.assertEquals(5, male_code.getControlDigitValue());
        Assert.assertEquals(3, female_code.getControlDigitValue());
        Assert.assertEquals(6, invalid_code.getControlDigitValue());
        Assert.assertEquals(ITNDecode.UNKNOWN, short_code.getControlDigitValue());
    }

    @Test
    public void testGetSex() throws Exception {
        Assert.assertEquals(ITNDecode.Sex.MALE, male_code.getSex());
        Assert.assertEquals(ITNDecode.Sex.FEMALE, female_code.getSex());
    }

    @Test
    public void testGetBirthday() throws Exception {
        Calendar expected = Calendar.getInstance();
        expected.clear();
        expected.set(1980, Calendar.NOVEMBER, 28);
        Assert.assertEquals(expected, male_code.getBirthday());
        expected.set(1983, Calendar.MARCH, 19);
        Assert.assertEquals(expected, female_code.getBirthday());
        Assert.assertEquals(null, invalid_code.getBirthday());
        Assert.assertEquals(null, short_code.getBirthday());

    }
}