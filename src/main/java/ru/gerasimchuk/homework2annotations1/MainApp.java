package ru.gerasimchuk.homework2annotations1;


import java.lang.reflect.InvocationTargetException;

public class MainApp {

    public static void main(String[] args) {
        Object[] objects = null;
        try {
            objects = RandomIntSetterInvoker.getObjects("ru.gerasimchuk.homework2annotations1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //
        for(Object obj: objects){
            if (obj!=null) {
                System.out.print(obj.getClass().getName() + " ");
                System.out.println(obj.toString());
            }
        }
    }
}
