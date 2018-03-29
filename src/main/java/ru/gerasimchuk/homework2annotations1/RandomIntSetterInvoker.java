package ru.gerasimchuk.homework2annotations1;



import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomIntSetterInvoker {


    private static List<Object> objectList = new LinkedList<>();

    public static Object[] getObjects(String basePackage) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        // парсим строку basePackage
        char[] basePackageChars = basePackage.toCharArray();
        StringBuilder stringBuilder = new StringBuilder("");
        List<String> list = new LinkedList<>();
        for(int i = 0; i < basePackageChars.length; i++){
            if (basePackageChars[i]!='.'){
                stringBuilder.append(basePackageChars[i]);
            }
            else {
                list.add(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
        list.add(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        int num = list.size();
        //System.out.println("num = " + num);
        stringBuilder.delete(0, stringBuilder.length());
        for(int i = 0; i < num; i++){
            stringBuilder.append(list.get(i));
            if (i != num-1) stringBuilder.append("\\");
        }
        String p = stringBuilder.toString();
        //System.out.println("p = " + p);
        // создаем путь до папки с файлами .java
        String path = "C:\\Users\\Reaper\\IdeaProjects\\homeworkLevelUp2\\src\\main\\java\\" + p;    //+ basePackage;
        // создаем переменную - директорию
        File dir = new File(path);
        //if (dir.isDirectory()) System.out.println("!!!!!!! DIR!!!!!!!!");
        // получаем массив файлов
        File[] files = dir.listFiles();
        //System.out.println("files num = " + files.length);
        // для каждого файла создаем объект по имени файла, смотрим наличие аннотации у поля, если есть - задаем значение поля
        for(File f:files){
            //System.out.println("NextIteration");
            // получаем имя файла
            String fileName = f.getName();
            //System.out.println(fileName);
            if (f.isDirectory()){
                //System.out.println("in dir! try recursion!");
                getObjects(basePackage + "." + fileName );
            }
            else {
                // убираем из имени файла расширение ".java"
                char[] chars = fileName.toCharArray();
                // - ".java"
                char[] chars2 = new char[chars.length - 5];
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '.') {
                        break;
                    } else {
                        chars2[i] = chars[i];
                    }
                }
                String className;
                // создаем переменную с именем класса
                className = new String(chars2);
                //System.out.println("className = " + className);
                // создаем переменную-файл , связанную с файлом в файловой системе
                File classFile = new File(path + "\\" + fileName);
                //System.out.println(classFile.exists());
                // создаем объект класса ????
                Class cl = null;
                try {
                    cl = Class.forName(basePackage + "." + className);
                } catch (ClassNotFoundException e) {
                   // e.printStackTrace();
                }
                Object obj = null;
                Constructor constructor;
                try {
                    constructor = cl.getConstructor();
                    obj = constructor.newInstance();
                } catch (Exception e) {
                   // e.printStackTrace();
                }
                // рефлексия!
                // Class cl = obj.getClass();
                Field[] fields = null;
                try {
                    fields = cl.getDeclaredFields();
                } catch (Exception e) {
                  //  e.printStackTrace();
                }
                boolean annotationsFound = false;
                if (fields != null) {
                    for (Field field : fields) {
                        final RandomIntSetter randomIntSetterAnnotation = field.getAnnotation(RandomIntSetter.class);
                        if (randomIntSetterAnnotation != null) {
                            field.setAccessible(true);
                            field.set(obj, (new Random().nextInt(randomIntSetterAnnotation.max() - randomIntSetterAnnotation.min())) + randomIntSetterAnnotation.min());
                            //System.out.println("added: " + cl.getName());
                            annotationsFound = true;
                        }
                    }
                }
                if (annotationsFound) {
                    //res[currentIndex] = obj;
                    //currentIndex++;
                    objectList.add(obj);
                }
            }
        }
        Object[] res = new Object[objectList.size()];
        for(int i = 0; i < objectList.size(); i++){
            res[i] = objectList.get(i);
        }
        return res;
    }
}
