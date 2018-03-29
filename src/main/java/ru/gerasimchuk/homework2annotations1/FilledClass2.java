package ru.gerasimchuk.homework2annotations1;

public class FilledClass2 {
    @RandomIntSetter(min = 30, max = 40)
    private int var1;
    @RandomIntSetter(min = 50, max = 100)
    private int var2;
    private String var3;

    public FilledClass2(){
        var1 = 0;
        var2 = 0;
        var3 = "var3_fc2";
    }

    @Override
    public String toString() {
        return var1 + " " + var2 + " " + var3;
    }
}