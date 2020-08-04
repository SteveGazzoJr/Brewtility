package com.projectdave.brewtility.services;

import com.projectdave.brewtility.exceptions.BadDataException;
import com.projectdave.brewtility.logic.ABVCalculator;
import com.projectdave.brewtility.logic.SpecificGravityTemperatureConverter;

import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.AssertJUnit.assertEquals;

public class CalculationServiceComponentTest {
    @Spy
    ABVCalculator abvCalculator;

    @Spy
    SpecificGravityTemperatureConverter specificGravityTemperatureConverter;

    @InjectMocks
    CalculationService calculationService;

    final String initialReading = "1.065";
    final String actualTemperature = "80";
    final String calibrationTemperature = "60";


    @BeforeMethod
    public void init(){
        initMocks(this);
    }

    @Test
    public void testGetABV() throws BadDataException {

        float test;
        try{
            test = calculationService.getABV("1", "2");
        }
        catch(BadDataException b){
            throw b;
        }
        verify(abvCalculator, times(1)).calcABVAsPercentage(1,2);
        assertEquals(test, 131.25, 0);
    }

    @Test
    public void testGetABVNotNumbers(){
        try{
            float test = calculationService.getABV("test", "test");
        }
        catch(BadDataException b){
            assertEquals(b.getClass(), BadDataException.class);
        }
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

    @Test
    public void testGetABVStartingGravityLessThanZero() {
        try {
            float test = calculationService.getABV("-1.0", "1.0");
        }
        catch(BadDataException b){
            assertTrue(b.getClass() == BadDataException.class);
        }
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());
    }

    @Test
    public void testGetABVFinalGravityLessThanZero() {
        try {
            float test = calculationService.getABV("1.0", "-1.0");
        }
        catch(BadDataException b){
            assertEquals(b.getClass(), BadDataException.class);
        }
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());

    }

    @Test
    public void testGetABVImpossibleGravity() {
        try {
            float test = calculationService.getABV("1.0", "0.5");
        }
        catch(BadDataException b){
            assertEquals(b.getClass(), BadDataException.class);
        }
        verify(abvCalculator, times(0)).calcABVAsPercentage(anyFloat(),anyFloat());

    }

    @Test
    public void testGetHydrometerReading(){
        double actual = 0d;

        try {
            actual = calculationService.getCorrectedHydrometerReading(initialReading, actualTemperature, calibrationTemperature);
        } catch (BadDataException e) {
            e.printStackTrace();
        }

        assertEquals(1.067, actual);
        verify(specificGravityTemperatureConverter, times(1)).getCorrectedHydrometerRead(Double.valueOf(initialReading), Double.valueOf(actualTemperature), Double.valueOf(calibrationTemperature));
    }

    @Test
    public void testHydrometerBadData(){
        int totalExceptions = 0;

        if (doesGenerateExceptionInSGConverter("0", actualTemperature, calibrationTemperature)) {
            totalExceptions++;
        }
        if (doesGenerateExceptionInSGConverter(initialReading, "0", calibrationTemperature)) {
            totalExceptions++;
        }
        if (doesGenerateExceptionInSGConverter(initialReading, actualTemperature, "0")) {
            totalExceptions++;
        }
        assertEquals(3, totalExceptions);

    }

    private boolean doesGenerateExceptionInSGConverter(String initialReading, String actualTemperature, String calibrationTemperature){
        try{
            calculationService.getCorrectedHydrometerReading(initialReading, actualTemperature, calibrationTemperature);
        }
        catch(BadDataException b){
            assertEquals(b.getClass(), BadDataException.class);
            return true;
        }
        return false;
    }
}