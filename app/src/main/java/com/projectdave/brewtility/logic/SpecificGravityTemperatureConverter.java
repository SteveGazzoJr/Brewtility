package com.projectdave.brewtility.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecificGravityTemperatureConverter {

    public double getCorrectedHydrometerRead(double initialReading, double actualTemperature,
                                            double calibrationTemperature){

        final double constant1 = 1.00130346;
        final double constant2 = 0.000134722124;
        final double constant3 = 0.00000204052596;
        final double constant4 = 0.00000000232820948;

        final double actualTempBigDecimal = actualTemperature;
        final double calibrationTempBigDecimal = calibrationTemperature;
        final double initialReadingBigDecimal = initialReading;

        final double conversion1actual = constant2 * actualTempBigDecimal;
        final double conversion2actual = constant3 * actualTempBigDecimal;
        final double conversion3actual = constant4 * actualTempBigDecimal;

        final double conversion1calibration = constant2 * calibrationTempBigDecimal;
        final double conversion2calibration = constant3 * calibrationTempBigDecimal;
        final double conversion3calibration = constant4 * calibrationTempBigDecimal;

        final BigDecimal stage1Actual = new BigDecimal(constant1 - (conversion1actual)
                + (conversion2actual)
                - (conversion3actual));

        final BigDecimal stage1Calibration = new BigDecimal(constant1 - (conversion1calibration)
                + (conversion2calibration)
                - (conversion3calibration));

        final BigDecimal nonTerminatingProduct = new BigDecimal(
                stage1Calibration.divide(stage1Actual, 100, RoundingMode.FLOOR).toString());



        return  initialReadingBigDecimal * (nonTerminatingProduct.doubleValue());
    }

}
