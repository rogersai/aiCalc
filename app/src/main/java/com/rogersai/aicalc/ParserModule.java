package com.rogersai.aicalc;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParserModule {
    @Provides @Singleton static InputParser provideInputParser(SymbolParser sp) {
        return new InputParser(sp);
    }
    @Provides @Singleton static SymbolParser provideSymbolParser(OperatorParser op, GrouperParser gp, AtomParser ap) {
        return new SymbolParser(op, gp, ap);
    }
    @Provides @Singleton static GrouperParser provideGrouperParser() { return new GrouperParser();}
    @Provides @Singleton static OperatorParser provideOperatorParser() { return new OperatorParser(); }
    @Provides @Singleton static AtomParser provideAtomParser() {
        return new AtomParser();
    }
}
