package ru.gerasimchuk.homework5annotation2;

public class SerializationAnnotationMainApp {
    public static void main(String[] args) {
        ExampleClass exampleClass = new ExampleClass(1,"ex1");
        ExampleClass exampleClass2 = new ExampleClass(3,"ex2");
        ExampleClass exampleClass3 = new ExampleClass(4,"ex3");
        ExampleClass exampleClass4 = new ExampleClass(2,"ex4");


        ExampleClass2 exampleClass21 = new ExampleClass2(5, "ex21", "bfkb");
        ExampleClass2 exampleClass22 = new ExampleClass2(6, "ex22", "bsdsgs");
        ExampleClass2 exampleClass23 = new ExampleClass2(7, "ex23", "bfufysdfgu");

        System.out.println(SerializedByProcessor.process(exampleClass));
        System.out.println(SerializedByProcessor.process(exampleClass21));
    }
}
