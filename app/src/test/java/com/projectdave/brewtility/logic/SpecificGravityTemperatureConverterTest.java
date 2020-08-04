package com.projectdave.brewtility.logic;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SpecificGravityTemperatureConverterTest {

    SpecificGravityTemperatureConverter specificGravityTemperatureConverter =
            new SpecificGravityTemperatureConverter();
    @Test
    public void testGetCorrectedHydrometerRead() {
        double correctedSpecificGravity = specificGravityTemperatureConverter
                .getCorrectedHydrometerRead(1.065, 80, 60);

        assertEquals(correctedSpecificGravity, 1.0678527301241787, 0);
    }
}