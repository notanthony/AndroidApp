package com.example.servicenovigrad;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends TestCase {

    public void testOnCreate() {
    }

    public void testOnCustomerButtonClicked() {
    }

    public void testOnEmployeeButtonClicked() {
    }

    @Test
    public void testOnRegisterButtonClicked() {

        Register register = new Register();

        String inputPassword = "adminadmin";
        String inputPassword2 = "adminadmin";
        assertFalse(inputPassword.isEmpty()); //testing if a password is entered
        assertTrue(!inputPassword.isEmpty()); //another test to see a if password is entered
        Assert.assertTrue("password is at least 6 characters", inputPassword.length()>=6);
        assertEquals(inputPassword,inputPassword2); //testing if passwords match

    }
}