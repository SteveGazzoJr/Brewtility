package com.projectdave.brewtility.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.projectdave.brewtility.MainActivity;
import com.projectdave.brewtility.customExceptions.GravityLessThanZeroException;
import com.projectdave.brewtility.customExceptions.ImpossibleGravityException;
import com.projectdave.brewtility.logic.ABVCalculator;

public class ABVCalcService {

    ABVCalculator abvCalculator = new ABVCalculator();
    Context context;

    public ABVCalcService(ABVCalculator abvCalculator, Context context){
        this.abvCalculator = abvCalculator;
        this.context = context;
    }

    public float getABV(String startingGravityIn, String finalGravityIn) throws GravityLessThanZeroException, ImpossibleGravityException {
        float startingGravity =  0;
        float finalGravity = 0;
        try {
            startingGravity = Float.valueOf(startingGravityIn);
            finalGravity = Float.valueOf(finalGravityIn);
        }
        catch(NumberFormatException e){
            generateBadNumberDialog("Please ensure that both boxes contain a valid number");
            throw e;
        }
        if(startingGravity <= 0 || finalGravity <= 0){
            generateBadNumberDialog("Please ensure that starting and final gravity are greater than zero");
            throw new GravityLessThanZeroException();
        }
        if(finalGravity - startingGravity <= 0){
            generateBadNumberDialog("Starting gravity cannot be greater than final gravity");
            throw new ImpossibleGravityException();
        }
        return abvCalculator.calcABVAsPercentage(startingGravity, finalGravity);
    }

    private void generateBadNumberDialog(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        alertDialogBuilder.show();
    }
}
