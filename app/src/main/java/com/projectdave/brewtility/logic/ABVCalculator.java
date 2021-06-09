package com.projectdave.brewtility.logic;
//test commit to trigger SAST
public class ABVCalculator {

    public float calcABVAsPercentage(float startingGravity, float finalGravity){
        return (float)((finalGravity - startingGravity) *  131.25);
    }
}
