package com.projectdave.brewtility.services;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.projectdave.brewtility.MainActivity;
import com.projectdave.brewtility.logic.ABVCalculator;

public class ABVCalcService {

    ABVCalculator abvCalculator = new ABVCalculator();

    public float getABV(String startingGravityIn, String finalGravityIn){
        float startingGravity =  0;
        float finalGravity = 0;
        try {
            startingGravity = Float.valueOf(startingGravityIn);
            finalGravity = Float.valueOf(finalGravityIn);
        }
        catch(NumberFormatException e){

        }
        return abvCalculator.calcABVAsPercentage(startingGravity, finalGravity);
    }

    private void generateBadNumberDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new MainActivity().getApplicationContext());
        alertDialogBuilder.setMessage("Please ensure that both boxes contain a valid number");
        alertDialogBuilder.setPositiveButton("yes", null);
        alertDialogBuilder.create().show();
    }
}
