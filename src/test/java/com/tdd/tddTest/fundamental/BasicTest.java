package com.tdd.tddTest.fundamental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; //junit5는 jupiter api 사용


public class BasicTest {

    @Test
    public void Test(){
        Assertions.assertTrue(1 == 1);
    }
}
