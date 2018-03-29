package ru.gerasimchuk.homework3stacktests;

import org.junit.jupiter.api.*;

public class MyStackTest<T> {


    MyStack<T> stack;
    T data;

    @BeforeEach
    void setup(){
        stack = new MyStack<T>();
        data = (T)(new Object());
    }

    @Test
    void testPush_data_true(){
        boolean res = stack.push(data);
        Assertions.assertTrue(res);
    }

    @Test
    void testPush_null_false(){
        boolean res = stack.push(null);
        Assertions.assertFalse(res);
    }


    @Test
    void testPop_void_T() throws EmptyStackException {
        stack.push(data);
        Object get = stack.pop();
        boolean result = ((T)get) != null;
        Assertions.assertTrue(result);
    }

    @Test
    void testPop_void_EmptyStackException(){
        Assertions.assertThrows(EmptyStackException.class, ()-> {stack.pop();});
    }

    @Test
    void testPeek_void_T() throws EmptyStackException {
        stack.push(data);
        Object get = stack.peek();
        boolean result = (T)get != null;
        Assertions.assertTrue(result);
    }

    @Test
    void testPeek_void_EmptyStackException(){
        Assertions.assertThrows(EmptyStackException.class, ()-> {stack.peek();});
    }

}
