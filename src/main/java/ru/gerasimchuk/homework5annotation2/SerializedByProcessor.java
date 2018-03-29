package ru.gerasimchuk.homework5annotation2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SerializedByProcessor {
    public static String process (Object obj){
        Class cl = obj.getClass();
        String result = null;
        final SerializedBy annotation = (SerializedBy) cl.getAnnotation(SerializedBy.class);
        if (annotation!=null){
            Class clazzSerialiser = annotation.value();
            Serializer serializer = null;
            try {
                Constructor constructor = clazzSerialiser.getDeclaredConstructor();
                serializer = (Serializer) constructor.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            Method[] methods = clazzSerialiser.getDeclaredMethods();
            for(Method m : methods){
                if (m.getName().equals("serialize")) {
                    try {
                        // arguments ?...
                        result = (String) m.invoke(serializer, obj);
                        break;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else throw new RuntimeException();
        return result;
    }
}
