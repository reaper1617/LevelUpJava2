package ru.gerasimchuk.homework2annotations1;

public class FilledClass {
    @RandomIntSetter(min = 10, max = 20)
    private int var1;
    private double var2;
    private String var3;

    public FilledClass(){
        var1 = 0;
        var2 = 0;
        var3 = "var3_fc";
    }

    @Override
    public String toString() {
        return var1 + " " + var2 + " " + var3;
    }
}
