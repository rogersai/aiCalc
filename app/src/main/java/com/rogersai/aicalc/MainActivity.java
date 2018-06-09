package com.rogersai.aicalc;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rogersai.aicalc.backend.CalcBackend;
import com.rogersai.aicalc.cloudlayout.CloudLayout;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private ConstraintLayout cloudContainer;
    private CloudLayout cloudLayout;

    private LinearLayout tapeStripLayout;
    private Button toTapeButton;

    private TextView inputView, outputView;

    private Button buttonMultiply, buttonDivide, buttonSubtract, buttonAdd, buttonEnter, buttonClear;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentManager fragmentManager;

    //private CalcBackendInterface calc;
    private CalcBackend calc;
    private Parser parser;
    private Evaluator evaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calc = DaggerCalcBackendInterface.create();
        calc = CalcBackend.newInstance(this);
        parser = calc.parser();
        evaluator = calc.evaluator();
        inputView = calc.input();
        outputView = calc.output();
        fragmentManager = getSupportFragmentManager();

        cloudContainer = (ConstraintLayout) findViewById(R.id.cloudContainer);
        cloudLayout = CloudLayout.newInstance();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment cl = (Fragment) cloudLayout;
        ft.add(cloudContainer.getId(), cl, "cloudLayout");
        ft.commit();

        tabLayout = (TabLayout) findViewById(R.id.atomTabLayout);
        viewPager = (ViewPager) findViewById(R.id.atomViewPager);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide= (Button) findViewById(R.id.buttonDivide);
        buttonSubtract= (Button) findViewById(R.id.buttonSubtract);
        buttonAdd= (Button) findViewById(R.id.buttonAdd);
        buttonEnter= (Button) findViewById(R.id.buttonEnter);
        buttonClear= (Button) findViewById(R.id.buttonClear);
        toTapeButton = (Button) findViewById(R.id.toTapeButton);
        //inputView = (TextView) findViewById(R.id.inputView);
        outputView = (TextView) findViewById(R.id.outputView);
        tapeStripLayout = (LinearLayout) findViewById(R.id.tapeStripLayout);

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText()+"x");
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText()+"/");
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText()+"-");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText()+"+");
            }
        });
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberAtom n = (NumberAtom) evaluator.input().evaluate(parser.input().parse(inputView.getText().toString()));
                outputView.setText(Double.toString(n.getValue()));
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText("");
            }
        });
        toTapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemID = 0;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment tapeItem= TapeLayoutItem.newInstance(inputView.getText().toString());
                if(tapeItem.getArguments() != null) {
                    itemID = tapeItem.getArguments().getInt("itemID");
                }
                System.out.println("tape" + itemID + " added");
                fragmentTransaction.add(tapeStripLayout.getId(), tapeItem, "tape" + itemID);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab){

    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab){

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab){

    }
}
