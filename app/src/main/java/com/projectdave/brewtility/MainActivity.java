package com.projectdave.brewtility;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projectdave.brewtility.customExceptions.GravityLessThanZeroException;
import com.projectdave.brewtility.customExceptions.ImpossibleGravityException;
import com.projectdave.brewtility.logic.ABVCalculator;
import com.projectdave.brewtility.services.ABVCalcService;
import com.projectdave.brewtility.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ABVCalculator abvCalculator = new ABVCalculator();
    ABVCalcService abvCalcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        abvCalcService = new ABVCalcService(abvCalculator, this.getApplicationContext());
    }

    public void calcAbv(View view) throws ImpossibleGravityException, GravityLessThanZeroException {
        TextView abvTextOut = (TextView)findViewById(R.id.txtAbvOut);
        EditText startingGravityIn = (EditText)findViewById(R.id.nmbrStartingGravity);
        EditText finalGravityIn = (EditText)findViewById(R.id.nmbrFinalGravity);
        abvTextOut.setText(Float.toString(abvCalcService.getABV(startingGravityIn.getText().toString(), finalGravityIn.getText().toString())));
    }
}