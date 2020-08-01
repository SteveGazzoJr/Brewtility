package com.projectdave.brewtility.services;

import com.projectdave.brewtility.MainActivity;
import com.projectdave.brewtility.logic.ABVCalculator;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;

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
    public void testGetABV() {
        abvCalcService.getABV("1", "1");
        verify(abvCalculator, times(1)).calcABVAsPercentage(1,1);
    }

    @Test(expectedExceptions = {NumberFormatException.class})
    public void testGetABVBadData() {
        abvCalcService.getABV("test", "test");
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

}