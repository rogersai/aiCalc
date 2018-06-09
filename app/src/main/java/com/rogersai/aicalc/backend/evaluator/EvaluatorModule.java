package com.rogersai.aicalc.backend.evaluator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EvaluatorModule {
    @Provides @Singleton static InputEvaluator provideInputEvaluator() {
        return new InputEvaluator();
    }
}
