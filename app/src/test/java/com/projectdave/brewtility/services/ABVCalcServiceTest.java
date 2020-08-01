package com.projectdave.brewtility.services;

import com.projectdave.brewtility.customExceptions.GravityLessThanZeroException;
import com.projectdave.brewtility.customExceptions.ImpossibleGravityException;
import com.projectdave.brewtility.logic.ABVCalculator;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ABVCalcServiceTest {
    @Mock
    ABVCalculator abvCalculator;


    @InjectMocks
    ABVCalcService abvCalcService;

    @BeforeClass
    public void init(){
        initMocks(this);
    }

    @Test
    public void testGetABV() throws ImpossibleGravityException, GravityLessThanZeroException {
        abvCalcService.getABV("1", "2");
        verify(abvCalculator, times(1)).calcABVAsPercentage(1,2);
    }

    @Test(expectedExceptions = {NumberFormatException.class})
    public void testGetABVNotNumbers() throws ImpossibleGravityException, GravityLessThanZeroException {
        abvCalcService.getABV("test", "test");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

    @Test(expectedExceptions = {GravityLessThanZeroException.class})
    public void testGetABVStartingGravityLessThanZero() throws ImpossibleGravityException, GravityLessThanZeroException {
        abvCalcService.getABV("-1.0", "1.0");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

    @Test(expectedExceptions = {GravityLessThanZeroException.class})
    public void testGetABVFinalGravityLessThanZero() throws ImpossibleGravityException, GravityLessThanZeroException {
        abvCalcService.getABV("1.0", "-1.0");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

    @Test(expectedExceptions = {ImpossibleGravityException.class})
    public void testGetABVImpossibleGravity() throws ImpossibleGravityException, GravityLessThanZeroException {
        abvCalcService.getABV("1.0", "0.5");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

}