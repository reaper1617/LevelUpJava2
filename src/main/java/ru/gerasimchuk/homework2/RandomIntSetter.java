package ru.gerasimchuk.homework2;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomIntSetter {
    int max(); // параметры аннотации
    int min(); // параметры аннотации
}
