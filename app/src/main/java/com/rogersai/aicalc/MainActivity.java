package com.rogersai.aicalc;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rogersai.aicalc.backend.CalcBackend;
import com.rogersai.aicalc.atominput.AtomFragment;
import com.rogersai.aicalc.cloud.CloudFragment;
import com.rogersai.aicalc.register.RegisterFragment;


public class MainActivity extends AppCompatActivity {
    private CalcBackend calc;
    private FragmentManager fm;

    private LinearLayout tapeStripLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = CalcBackend.newInstance(this);
        fm = getSupportFragmentManager();

        setupOperatorPad();
        setupTape();

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

        CloudFragment cloudFragment = CloudFragment.getInstance();
        RegisterFragment registerFragment = RegisterFragment.getInstance();
        AtomFragment atomFragment = AtomFragment.getInstance();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        ConstraintLayout cloudContainer = findViewById(R.id.cloudContainer);
        fragmentTransaction.add(cloudContainer.getId(), cloudFragment, "cloudLayout");

        ConstraintLayout registerContainer = findViewById(R.id.registerContainer);
        fragmentTransaction.add(registerContainer.getId(), registerFragment, "registerLayout");

        ConstraintLayout tabContainer = findViewById(R.id.tabContainer);
        fragmentTransaction.add(tabContainer.getId(), atomFragment, "tabLayout");

        fragmentTransaction.commit();

        calc.setCloud(cloudFragment.getCloudBackend());
        calc.setRegister(registerFragment.getRegisterBackend());
        calc.setAtomBackend(atomFragment.getAtomBackend());

    }
    protected void setupTape(){
        Button toTapeButton = findViewById(R.id.toTapeButton);
        Button toRegisterButton = findViewById(R.id.buttonInputToRegister);
        tapeStripLayout = findViewById(R.id.tapeStripLayout);

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
    }
    protected void setupOperatorPad() {
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonEnter = findViewById(R.id.buttonEnter);
        Button buttonClear = findViewById(R.id.buttonClear);

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
    }

}
