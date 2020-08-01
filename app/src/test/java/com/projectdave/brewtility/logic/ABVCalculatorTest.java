package com.projectdave.brewtility.logic;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ABVCalculatorTest {
    ABVCalculator abvCalculator = new ABVCalculator();

    @Test
    public void testCalcABVAsPercentage() {
        float test = abvCalculator.calcABVAsPercentage(9, 10);
        assertEquals(test, 131.25, 0);
    }
}