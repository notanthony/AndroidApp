package com.example.servicenovigrad;

import junit.framework.TestCase;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerCitySearchTest extends TestCase {
    @Test
    public void customerCitySearchTest(){
        String testCity = "Ottawa";
        CustomerSearchBranches Test = new CustomerSearchBranches();
        boolean result = Test.validateCitySearchFieldInput(testCity);
        assertThat(result, is(true));

    }
}
