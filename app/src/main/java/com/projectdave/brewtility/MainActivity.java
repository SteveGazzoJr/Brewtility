package com.projectdave.brewtility;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.projectdave.brewtility.exceptions.BadDataException;
import com.projectdave.brewtility.logic.ABVCalculator;
import com.projectdave.brewtility.logic.SpecificGravityTemperatureConverter;
import com.projectdave.brewtility.services.CalculationService;
import com.projectdave.brewtility.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ABVCalculator abvCalculator = new ABVCalculator();
    SpecificGravityTemperatureConverter specificGravityTemperatureConverter = new SpecificGravityTemperatureConverter();
    CalculationService calculationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        calculationService = new CalculationService(abvCalculator, specificGravityTemperatureConverter, this.getApplicationContext());
    }

    public void calcAbv(View view) {
        TextView abvTextOut = (TextView)findViewById(R.id.txtAbvOut);
        EditText startingGravityIn = (EditText)findViewById(R.id.nmbrStartingGravity);
        EditText finalGravityIn = (EditText)findViewById(R.id.nmbrFinalGravity);
        try {
            float abv = calculationService.getABV(startingGravityIn.getText().toString(), finalGravityIn.getText().toString());
            abvTextOut.setText(Float.toString(abv));
        }
        catch(BadDataException b){
            abvTextOut.setText("Data input error");
        }
    }
}