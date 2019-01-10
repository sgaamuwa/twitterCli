package com.gaamuwa.twitter;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTestClass {

    @Test
    public void sampleTest(){
        Assert.assertEquals((2*2), 4, "make sure that this is running");
    }
}
