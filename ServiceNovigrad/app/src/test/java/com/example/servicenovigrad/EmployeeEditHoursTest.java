package com.example.servicenovigrad;

import junit.framework.TestCase;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmployeeEditHoursTest extends TestCase {
    @Test
    public static void employeeEditHourTest(){
        String hour = "07:00 AM";
        EmployeeEditHours Test = new EmployeeEditHours();
        boolean result = Test.validateBranchHourChange(hour);
        assertThat(result, is(true));

    }



}
