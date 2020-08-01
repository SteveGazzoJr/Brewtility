package com.projectdave.brewtility.services;

import android.content.Context;
import com.projectdave.brewtility.logic.ABVCalculator;

public class ABVCalcService {

    ABVCalculator abvCalculator = new ABVCalculator();
    Context context;

    public ABVCalcService(ABVCalculator abvCalculator, Context context){
        this.abvCalculator = abvCalculator;
        this.context = context;
    }

    public float getABV(String startingGravityIn, String finalGravityIn){
        float startingGravity =  0;
        float finalGravity = 0;
        try {
            startingGravity = Float.valueOf(startingGravityIn);
            finalGravity = Float.valueOf(finalGravityIn);
        }
        catch(NumberFormatException e){
            handleBadNumbers("Please ensure that both boxes contain a valid number");
            return 0;
        }
        if(startingGravity <= 0 || finalGravity <= 0){
            handleBadNumbers("Please ensure that starting and final gravity are greater than zero");
            return 0;
        }
        if(finalGravity - startingGravity <= 0){
            handleBadNumbers("Starting gravity cannot be greater than final gravity");
            return 0;
        }
        return abvCalculator.calcABVAsPercentage(startingGravity, finalGravity);
    }

    private void handleBadNumbers(String message){

    }
}
