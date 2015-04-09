package com.github.deniszpua.itndecode.decoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ITNDecoderTest {
    private static final String MALE_ITN_STRING = "2955210070";
    private static final String FEMALE_ITN_STRING = "3039315785";
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
        Assert.assertEquals(0, male_code.getControlDigitValue());
        Assert.assertEquals(5, female_code.getControlDigitValue());
        Assert.assertEquals(9, invalid_code.getControlDigitValue());
    }

    @Test
    public void testGetSex() throws Exception {

    }
}