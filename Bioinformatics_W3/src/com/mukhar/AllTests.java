package com.mukhar;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MotifEnumerationTest.class, ProfilerTest.class, MotifFinderTest.class,
        MotifGreedyFinderTest.class })
public class AllTests {

}
