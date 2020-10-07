package com.example.servicenovigrad;

import junit.framework.TestCase;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends TestCase {

    public void testOnCreate() {
    }

    public void testOnCustomerButtonClicked() {
    }

    public void testOnEmployeeButtonClicked() {
    }

    public void testOnRegisterButtonClicked() {

        Register register = new Register();

        String inputPassword = "adminadmin";
        String inputPassword2 = "adminadmin";
        assertEquals(inputPassword,inputPassword2);
    }
}