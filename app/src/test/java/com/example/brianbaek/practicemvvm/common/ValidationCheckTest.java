package com.example.brianbaek.practicemvvm.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationCheckTest {

    @Test
    public void testTextHasSpace(){
        assertEquals(true, ValidationCheck.isSpaceInText("abc def"));
    }

}