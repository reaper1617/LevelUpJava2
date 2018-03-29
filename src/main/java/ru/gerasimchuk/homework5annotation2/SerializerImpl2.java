package ru.gerasimchuk.homework5annotation2;

import java.lang.reflect.Field;

public class SerializerImpl2 implements Serializer{
    @Override
    public String serialize(Object obj) {
        // тут будет рефлексия!
        StringBuilder stringBuilder = new StringBuilder("Serialilzed by impl2! ");
        Class cl = obj.getClass();
        Field[] fields = cl.getDeclaredFields();
        for(Field f : fields){
            f.setAccessible(true);
            try {
                stringBuilder.append(f.getName() + " = " + f.get(obj) + "; ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
