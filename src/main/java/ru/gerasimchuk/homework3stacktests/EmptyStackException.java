package ru.gerasimchuk.homework3stacktests;

public class EmptyStackException extends Exception {
    public EmptyStackException(){
        super("Stack is empty");
    }
}
