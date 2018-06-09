package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.evaluator.EvaluatorModule;
import com.rogersai.aicalc.backend.evaluator.InputEvaluator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = EvaluatorModule.class)
public interface Evaluator {
    InputEvaluator input();
}
