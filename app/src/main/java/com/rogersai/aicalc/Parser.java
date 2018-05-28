package com.rogersai.aicalc;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ParserModule.class)
public interface Parser {
    InputParser input();
    SymbolParser symbol();
    OperatorParser operator();
    AtomParser atom();
}
