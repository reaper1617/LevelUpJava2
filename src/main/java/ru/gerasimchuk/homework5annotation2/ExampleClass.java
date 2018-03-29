package ru.gerasimchuk.homework5annotation2;

@SerializedBy(value = SerializerImpl.class)
public class ExampleClass {

    private int field1;
    private String field2;

    public ExampleClass(){
        this.field1 = 0;
        this.field2 = "empty";
    }

    public ExampleClass(int val1, String val2){
        this.field1 = val1;
        this.field2 = val2;
    }
}
