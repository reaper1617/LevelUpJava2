package ru.gerasimchuk.homework3;

import org.junit.jupiter.api.*;

import java.util.Random;


public class MyListTest<T> {

    T node;
    MyList<T> list;


    @BeforeEach
    public void setup() {
        node = (T) (new Object());
        list = new MyList<T>();
    }

    @Test
    @RepeatedTest(10)
    public void testAdd_T_returnTrue() {
        boolean res = list.add(node);
        Assertions.assertTrue(res);
    }

    @Test
    @RepeatedTest(100)
    public void testAddToPos_int_returnIndexOutOfBoundException() {
        final int index = list.getSize() + new Random().nextInt(100);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(node, index);
        });
    }

    @Test
    @RepeatedTest(100)
    public void testAddToPos_int_returnTrue(){
        final int upperBound = new Random().nextInt(100);
        for(int i = 0; i < upperBound; i++){
            list.add(node);
        }
        final int index = new Random().nextInt(list.getSize());
        boolean res = list.add(node,index);
        Assertions.assertTrue(res);
    }


    @Test
    @RepeatedTest(100)
    public void testGet_int_IndexOutOfBoundEx(){
        final int upperBound = new Random().nextInt(100);
        for(int i = 0; i < upperBound; i++){
            list.add(node);
        }
        final int index = list.getSize() + new Random().nextInt(list.getSize());
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->list.get(index));
    }

    @Test
    @RepeatedTest(100)
    public void testGet_Node_notNull(){
        final int upperBound = new Random().nextInt(100);
        for(int i = 0; i < upperBound; i++){
            list.add(node);
        }
        final int index = new Random().nextInt(list.getSize());
        Object obj = list.get(index);
        boolean res = (obj!=null);
        Assertions.assertTrue(res);
    }

    @Test
    @RepeatedTest(100)
    public void testRemove_int_returnFalse(){
        final int upperBound = new Random().nextInt(100);
        for(int i = 0; i < upperBound; i++){
            list.add(node);
        }
        final int index = list.getSize() + new Random().nextInt(list.getSize());
        boolean res = list.remove(index);
        Assertions.assertFalse(res);
    }

    @Test
    @RepeatedTest(100)
    public void testRemove_int_returnTrue(){
        final int upperBound = new Random().nextInt(99)+1;
        for(int i = 0; i < upperBound; i++){
            list.add(node);
        }
        final int index = new Random().nextInt(list.getSize());
        boolean res = list.remove(index);
        Assertions.assertTrue(res);
    }
}
