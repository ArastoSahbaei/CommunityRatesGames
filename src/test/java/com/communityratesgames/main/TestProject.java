package com.communityratesgames.main;

import com.communityratesgames.tests.CompanyTest;
import com.communityratesgames.tests.UserTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestProject {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UserTest.class, CompanyTest.class);

        for (Failure failure: result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
