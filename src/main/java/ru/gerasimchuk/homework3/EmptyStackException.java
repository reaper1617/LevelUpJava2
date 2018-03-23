package ru.gerasimchuk.homework3;

public class EmptyStackException extends Exception {
    public EmptyStackException(){
        super("Stack is empty");
    }
}
