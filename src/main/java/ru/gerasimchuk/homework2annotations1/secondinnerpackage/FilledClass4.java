package ru.gerasimchuk.homework2annotations1.secondinnerpackage;

import ru.gerasimchuk.homework2annotations1.RandomIntSetter;

public class FilledClass4 {

    private int var1;
    @RandomIntSetter(min = 50, max = 100)
    private int var2;
    private String var3;

    public FilledClass4(){
        var1 = 0;
        var2 = 0;
        var3 = "var3_fc4";
    }

    @Override
    public String toString() {
        return var1 + " " + var2 + " " + var3;
    }
}
