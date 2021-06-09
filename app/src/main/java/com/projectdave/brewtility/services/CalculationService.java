package com.projectdave.brewtility.services;

import android.content.Context;

import com.projectdave.brewtility.exceptions.BadDataException;
import com.projectdave.brewtility.logic.ABVCalculator;
import com.projectdave.brewtility.logic.SpecificGravityTemperatureConverter;

import java.text.DecimalFormat;
//test commit comment
public class CalculationService {

    ABVCalculator abvCalculator = new ABVCalculator();
    SpecificGravityTemperatureConverter specificGravityTemperatureConverter = new SpecificGravityTemperatureConverter();
    Context context;

    public CalculationService(ABVCalculator abvCalculator, SpecificGravityTemperatureConverter specificGravityTemperatureConverter, Context context){
        this.abvCalculator = abvCalculator;
        this.specificGravityTemperatureConverter = specificGravityTemperatureConverter;
        this.context = context;
    }

    public float getABV(String startingGravityIn, String finalGravityIn) throws BadDataException {
        float startingGravity =  0;
        float finalGravity = 0;
        try {
            startingGravity = Float.valueOf(startingGravityIn);
            finalGravity = Float.valueOf(finalGravityIn);
        }
        catch(NumberFormatException e){
            throw new BadDataException();
        }
        if(startingGravity <= 0 || finalGravity <= 0){
            throw new BadDataException();
        }
        if(finalGravity - startingGravity <= 0){
            throw new BadDataException();
        }
        return abvCalculator.calcABVAsPercentage(startingGravity, finalGravity);
    }

    public double getCorrectedHydrometerReading(String initialReading, String actualTemperature, String calibrationTemperature) throws BadDataException {
        double initialReadingDouble,
                actualTemperatureDouble,
                calibrationTemperatureDouble;
        try{
            initialReadingDouble = Double.valueOf(initialReading);
            actualTemperatureDouble = Double.valueOf(actualTemperature);
            calibrationTemperatureDouble = Double.valueOf(calibrationTemperature);
        }
        catch(Exception e){
            throw new BadDataException();
        }
        if(initialReadingDouble <=0 ||
        actualTemperatureDouble <=0 ||
        calibrationTemperatureDouble <=0)
            throw new BadDataException();

        final double correctedHydrometerRead = specificGravityTemperatureConverter.getCorrectedHydrometerRead(initialReadingDouble, actualTemperatureDouble, calibrationTemperatureDouble);
        return Double.valueOf(Double.toString(correctedHydrometerRead).substring(0,5));
    }
}
