package com.projectdave.brewtility.services;

import com.projectdave.brewtility.logic.ABVCalculator;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.AssertJUnit.assertEquals;

public class ABVCalcServiceTest {
    @Mock
    ABVCalculator abvCalculator;

    @InjectMocks
    ABVCalcService abvCalcService;

    @BeforeMethod
    public void init(){
        initMocks(this);
    }

    @Test
    public void testGetABV() {
        float test = abvCalcService.getABV("1", "2");
        verify(abvCalculator, times(1)).calcABVAsPercentage(1,2);
    }

    @Test
    public void testGetABVNotNumbers(){
        float test = abvCalcService.getABV("test", "test");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
        assertEquals(test, 0, 0);
    }

    @Test
    public void testGetABVStartingGravityLessThanZero() {
        float test = abvCalcService.getABV("-1.0", "1.0");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
        assertEquals(test, 0, 0);
    }

    @Test
    public void testGetABVFinalGravityLessThanZero() {
        float test = abvCalcService.getABV("1.0", "-1.0");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
        assertEquals(test, 0, 0);
    }

    @Test
    public void testGetABVImpossibleGravity() {
        float test = abvCalcService.getABV("1.0", "0.5");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
        assertEquals(test, 0, 0);
    }

}