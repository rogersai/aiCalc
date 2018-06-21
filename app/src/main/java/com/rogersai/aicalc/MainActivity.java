package com.rogersai.aicalc;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rogersai.aicalc.atominput.AtomBackend;
import com.rogersai.aicalc.atominput.AtomFragment;
import com.rogersai.aicalc.backend.CalcBackend;
import com.rogersai.aicalc.cloud.CloudBackend;
import com.rogersai.aicalc.cloud.CloudFragment;
import com.rogersai.aicalc.register.RegisterBackend;
import com.rogersai.aicalc.register.RegisterFragment;
import com.rogersai.aicalc.symbol.atom.Atom;


public class MainActivity extends AppCompatActivity {
    private CalcBackend calc;
    private AtomFragment atomFragment;
    private CloudFragment cloudFragment;
    private RegisterFragment registerFragment;
    private FragmentManager fm;

    private LinearLayout tapeStripLayout;
    private Button toTapeButton, toRegisterButton;

    private Button buttonMultiply, buttonDivide, buttonSubtract, buttonAdd, buttonEnter, buttonClear;

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = CalcBackend.newInstance(this);
        fm = getSupportFragmentManager();

        toTapeButton = findViewById(R.id.toTapeButton);
        toRegisterButton = findViewById(R.id.buttonInputToRegister);
        tapeStripLayout = findViewById(R.id.tapeStripLayout);

        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide= findViewById(R.id.buttonDivide);
        buttonSubtract= findViewById(R.id.buttonSubtract);
        buttonAdd= findViewById(R.id.buttonAdd);
        buttonEnter= findViewById(R.id.buttonEnter);
        buttonClear= findViewById(R.id.buttonClear);

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("x");
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("/");
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("-");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("+");
            }
        });
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.evaluateInput();
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.clear();
            }
        });
        toTapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemID = 0;
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Fragment tapeItem= TapeLayoutItem.newInstance(calc.reportInput());
                if(tapeItem.getArguments() != null) {
                    itemID = tapeItem.getArguments().getInt("itemID");
                }
                System.out.println("tape" + itemID + " added");
                fragmentTransaction.add(tapeStripLayout.getId(), tapeItem, "tape" + itemID);
                fragmentTransaction.commit();
            }
        });
        toRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.registerInput();
            }
        });
        findViewById(R.id.mainLayout).setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){
            @Override
            public void onSwipeLeft() {
                findViewById(R.id.registerContainer).setVisibility(View.GONE);
            }
            @Override
            public void onSwipeRight() {
                findViewById(R.id.registerContainer).setVisibility(View.VISIBLE);
            }
        });
        cloudFragment = CloudFragment.getInstance();
        registerFragment = RegisterFragment.getInstance();
        atomFragment = AtomFragment.getInstance();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        ConstraintLayout cloudContainer = (ConstraintLayout) findViewById(R.id.cloudContainer);
        Fragment cf = (Fragment) cloudFragment;
        fragmentTransaction.add(cloudContainer.getId(), cf, "cloudLayout");

        ConstraintLayout registerContainer = (ConstraintLayout) findViewById(R.id.registerContainer);
        Fragment rf = (Fragment) registerFragment;
        fragmentTransaction.add(registerContainer.getId(), rf, "registerLayout");

        ConstraintLayout tabContainer = (ConstraintLayout) findViewById(R.id.tabContainer);
        Fragment af = (Fragment) atomFragment;
        fragmentTransaction.add(tabContainer.getId(), af, "tabLayout");

        fragmentTransaction.commit();

        calc.setCloud(cloudFragment.getCloudBackend());
        calc.setRegister(registerFragment.getRegisterBackend());
        calc.setAtomBackend(atomFragment.getAtomBackend());

    }

}
