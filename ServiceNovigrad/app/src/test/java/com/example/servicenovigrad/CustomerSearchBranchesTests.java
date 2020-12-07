package com.example.servicenovigrad;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class CustomerSearchBranchesTests {

    Random random;
    @Test
    public void customerSearchRetrieveEmployeeTest1(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode("A1A 1Z1");
        testAddr.setStreet(charList);
        testAddr.setProvince(Address.Province.AB);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(true));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest2(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode(charList);
        testAddr.setStreet("123 sesame street");
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(false));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest3(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode("123345");
        testAddr.setStreet(null);
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(false));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest4(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode(null);
        testAddr.setStreet(null);
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(true));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest5(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(null);
        testAddr.setPostalCode(null);
        testAddr.setStreet("elm street");
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(true));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest6(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(null);
        testAddr.setPostalCode(null);
        testAddr.setStreet(null);
        testAddr.setProvince(Address.Province.SK);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(true));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest7(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode(null);
        testAddr.setStreet(charList);
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(true));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest8(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode(charList);
        testAddr.setStreet(charList);
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(false));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest9(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity("england");
        testAddr.setPostalCode(null);
        testAddr.setStreet(null);
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(false));

    }

    @Test
    public void customerSearchRetrieveEmployeeTest10(){
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        String testName = "";
//        String testId = "";
//        String testCity = "";
//        String testStreet = "";
//        for (int i = 0; i< 10; i++) {
//            testName += charList.charAt(random.nextInt(charList.length()));
//            testId += charList.charAt(random.nextInt(charList.length()));
//            testCity += charList.charAt(random.nextInt(charList.length()));
//            testStreet += charList.charAt(random.nextInt(charList.length()));
//        }
        CustomerSearchBranches Test = new CustomerSearchBranches();
        Address testAddr = new Address();
        testAddr.setCity(charList);
        testAddr.setPostalCode(null);
        testAddr.setStreet("5");
        testAddr.setProvince(Address.Province.ON);
        boolean result = Test.validateCustomerSearchRetrieveBranch(charList, charList,"test@test.com", "613-123-4567", testAddr);
        assertThat(result, is(false));

    }
}
