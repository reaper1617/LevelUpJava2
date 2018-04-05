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
            Class clazzSerializer = annotation.value();
            Serializer serializer = null;
            try {
                Constructor constructor = clazzSerializer.getDeclaredConstructor();
                serializer = (Serializer) constructor.newInstance();
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            Method[] methods = clazzSerializer.getDeclaredMethods();
            for(Method m : methods){
                if (m.getName().equals("serialize")) {
                    try {
                        result = (String) m.invoke(serializer, obj);
                        break;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else throw new RuntimeException();
        return result;
    }
}
