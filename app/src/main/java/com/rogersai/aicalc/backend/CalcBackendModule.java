package com.rogersai.aicalc.backend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.DaggerEvaluator;
import com.rogersai.aicalc.DaggerParser;
import com.rogersai.aicalc.Evaluator;
import com.rogersai.aicalc.Parser;
import com.rogersai.aicalc.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CalcBackendModule extends Fragment {
    @Provides @Singleton static Parser provideParser() {
        return DaggerParser.create();
    }
    @Provides @Singleton static Evaluator provideEvaluator() {
        return DaggerEvaluator.create();
    }
}
