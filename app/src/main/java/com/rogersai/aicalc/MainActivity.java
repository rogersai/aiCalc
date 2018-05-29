package com.rogersai.aicalc;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonLParen, buttonRParen;
    private Button buttonMultiply, buttonDivide, buttonSubtract, buttonAdd, buttonEnter, buttonClear;
    private Button toTapeButton;
    private TextView inputView, preView;
    private LinearLayout tapeLayout;
    private Parser parser;
    private Evaluator evaluator;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parser = DaggerParser.create();
        evaluator = new Evaluator();
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
        inputView = (TextView) findViewById(R.id.inputView);
        preView = (TextView) findViewById(R.id.outputView);
        tapeLayout = (LinearLayout) findViewById(R.id.tapeLayout);

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
                NumberAtom n = (NumberAtom) evaluator.evaluate(parser.input().parse(inputView.getText().toString()));
                preView.setText(Double.toString(n.getValue()));
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText("");
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
