package com.gaamuwa.twitter;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;


/**
 * This is the setup class for the tests
 * All tests will extend this to be able to interact with the components in spring boot
 */
@SpringBootTest()
public class BaseTestClass extends AbstractTestNGSpringContextTests {

    @BeforeClass
    public void setUp(){
        // this will initialise all the classes that we annotate with mock
        MockitoAnnotations.initMocks(this);
    }
}
