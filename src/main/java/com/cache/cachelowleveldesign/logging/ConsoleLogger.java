package com.cache.cachelowleveldesign.logging;

public class ConsoleLogger implements Logger {
    @Override
    public void log(Object o) {
        System.out.println(o);
        System.out.println("------------------------------------------");
    }
}
