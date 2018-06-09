package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.AtomParser;
import com.rogersai.aicalc.backend.parser.InputParser;
import com.rogersai.aicalc.backend.parser.OperatorParser;
import com.rogersai.aicalc.backend.parser.ParserModule;
import com.rogersai.aicalc.backend.parser.SymbolParser;

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
