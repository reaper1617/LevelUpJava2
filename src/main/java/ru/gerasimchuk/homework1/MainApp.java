package ru.gerasimchuk.homework1;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainApp {



    public static void main(String[] args) {
        File input = new File("C:\\Users\\Reaper\\IdeaProjects\\LevelUpJava2\\src\\ru\\gerasimchuk\\homework1\\input.txt");
        String line;
        List<FilledClass> list = new LinkedList();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))){
            while ((line = reader.readLine()) != null){
                if (line.length()!=0) {
                    String[] strings = line.split(" ");
                    int var1 = Integer.parseInt(strings[0]);
                    double var2 = Double.parseDouble(strings[1]);
                    String var3 = strings[2];

                    FilledClass fc = new FilledClass();
                    Class cl = fc.getClass();
                    Field[] fields = cl.getDeclaredFields();
                    fields[0].setAccessible(true);
                    fields[0].set(fc,var1);
                    fields[1].setAccessible(true);
                    fields[1].set(fc,var2);
                    fields[2].setAccessible(true);
                    fields[2].set(fc,var3);

                    list.add(fc);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for(FilledClass fc: list){
            Class cl = fc.getClass();
            Method method;
            try {
                method = cl.getDeclaredMethod("concat");
                method.setAccessible(true);
                System.out.println(method.invoke(fc));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }



    }
}
