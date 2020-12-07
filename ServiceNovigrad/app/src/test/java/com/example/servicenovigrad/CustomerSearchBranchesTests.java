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
    public void customerSearchRetrieveEmployeeTest(){
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
}
