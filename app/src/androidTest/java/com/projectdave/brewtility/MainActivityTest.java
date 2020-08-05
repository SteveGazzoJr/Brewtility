package com.projectdave.brewtility;


import android.widget.Button;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void calcABVComponentTest() throws Exception {
        final MainActivity activity = rule.getActivity();
        activity.runOnUiThread(new Runnable() {
                                   public void run() {
                                       TextView startingGravity = activity.findViewById(R.id.nmbrStartingGravity);
                                       startingGravity.setText("1.065");
                                       TextView finalGravity = activity.findViewById(R.id.nmbrFinalGravity);
                                       finalGravity.setText("1.079");
                                       Button calcAbvButton = activity.findViewById(R.id.btnCalcAbv);
                                       TextView abvOut = activity.findViewById(R.id.txtAbvOut);

                                       calcAbvButton.performClick();
                                       assertEquals("1.837492", abvOut.getText());
                                   }
        });


    }

    @Test
    public void calcCorrectedSGComponentTest() throws Exception {
        final MainActivity activity = rule.getActivity();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                TextView initialReading = activity.findViewById(R.id.nmbrInitialReading);
                initialReading.setText("1.072");
                TextView actualTemp = activity.findViewById(R.id.nmbrActualTemp);
                actualTemp.setText("80");
                TextView calibrationTemp = activity.findViewById(R.id.nmbrCalibrationTemp);
                calibrationTemp.setText("60");
                Button getCorrectedReadingButton = activity.findViewById(R.id.btnConvert);
                TextView correctedReadingOut = activity.findViewById(R.id.lblConvertedReadingOut);

                getCorrectedReadingButton.performClick();
                assertEquals("1.074", correctedReadingOut.getText());
            }
        });


    }
}

