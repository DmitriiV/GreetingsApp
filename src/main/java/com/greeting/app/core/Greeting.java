package com.greeting.app.core;

public class Greeting {
    private final String whom;
    private final String when;

    public Greeting(String whom, String when) {
        this.whom = whom;
        this.when = when;
    }

    public String getWhom() {
        return whom;
    }

    public String getWhen() {
        return when;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "whom='" + whom + '\'' +
                ", when='" + when + '\'' +
                '}';
    }
}
