package com.example.servicenovigrad;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCitySearchTest {
    @Mock
    Context mMockContext;

    @Test
    public void customerCitySearchTest(){
        String testCity = "Ottawa";
        CustomerSearchBranches Test = new CustomerSearchBranches();
        boolean result = Test.validateCitySearchFieldInput(testCity);
        assertThat(result, is(true));

    }
}
