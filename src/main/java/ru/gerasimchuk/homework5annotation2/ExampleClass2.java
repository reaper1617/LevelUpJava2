package ru.gerasimchuk.homework5annotation2;

@SerializedBy(value = SerializerImpl2.class)
public class ExampleClass2 {

    private int field1;
    private String field2;
    private String field3;


    public ExampleClass2(){
        this.field1 = 0;
        this.field2 = "empty";
        this.field3 = "empty";

    }

    public ExampleClass2(int val1, String val2, String val3){
        this.field1 = val1;
        this.field2 = val2;
        this.field3 = val3;
    }
}
