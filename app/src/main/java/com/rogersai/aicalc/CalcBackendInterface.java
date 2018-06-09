package com.rogersai.aicalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.backend.CalcBackendModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = CalcBackendModule.class)
public interface CalcBackendInterface {
//    Parser parser();
//    Evaluator evaluator();
//    TextView input();
//    TextView output();

}
