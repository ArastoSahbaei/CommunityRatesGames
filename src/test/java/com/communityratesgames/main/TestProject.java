package com.communityratesgames.main;

import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.tests.*;
import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.inject.Inject;

public class TestProject {

    public static void main(String[] args) {
        RunTests rt = new RunTests();
        rt.runTests();
    }

}

class RunTests {
    private final static Logger logger = Logger.getLogger(com.communityratesgames.main.RunTests.class);

    public void runTests() {
        Result result = JUnitCore.runClasses(
                UserTest.class,
                CompanyTest.class,
                GameTest.class,
                RatingTest.class,
                PlatformTest.class
        );
    }
}


