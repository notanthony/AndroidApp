package com.example.servicenovigrad;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    private FirebaseAuth fba;
    @Test
    public void authenticateTest() {
        fba = FirebaseAuth.getInstance();
        assertEquals(4, 2 + 2);
    }

}