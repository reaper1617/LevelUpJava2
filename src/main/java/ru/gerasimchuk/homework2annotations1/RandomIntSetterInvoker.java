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

    private static String transformPackageName(String basePackage){
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
        return stringBuilder.toString();
    }

    private static String deleteJavaExFromClassName(String fileName){
        char[] chars = fileName.toCharArray();
        char[] chars2 = new char[chars.length - 5];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                break;
            } else {
                chars2[i] = chars[i];
            }
        }
        return  new String(chars2);
    }

    private static boolean fillIfAnnotationFound(Field[] fields, Object filledObj){
        boolean annotationsFound = false;
        if (fields != null) {
            for (Field field : fields) {
                final RandomIntSetter randomIntSetterAnnotation = field.getAnnotation(RandomIntSetter.class);
                if (randomIntSetterAnnotation != null) {
                    field.setAccessible(true);
                    try {
                        field.set(filledObj, (new Random().nextInt(randomIntSetterAnnotation.max() - randomIntSetterAnnotation.min())) + randomIntSetterAnnotation.min());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    annotationsFound = true;
                }
            }
        }
        return annotationsFound;
    }

    public static Object[] getObjects(String basePackage) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        String pName = transformPackageName(basePackage);
        String path = "C:\\Users\\Reaper\\IdeaProjects\\homeworkLevelUp2\\src\\main\\java\\" + pName;
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File f:files){
            String fileName = f.getName();
            if (f.isDirectory()) getObjects(basePackage + "." + fileName);
            else {
                String className = deleteJavaExFromClassName(fileName);
                File classFile = new File(path + "\\" + fileName);
                Class cl; Constructor constructor; Object obj = null; Field[] fields = null;
                try {
                    cl = Class.forName(basePackage + "." + className);
                    constructor = cl.getConstructor();
                    obj = constructor.newInstance();
                    fields = cl.getDeclaredFields();
                } catch (ClassNotFoundException | NoSuchMethodException e) {}
                boolean annotationsFound = fillIfAnnotationFound(fields, obj);
                if (annotationsFound) objectList.add(obj);
            }
        }
        Object[] res = new Object[objectList.size()];
        for(int i = 0; i < objectList.size(); i++) res[i] = objectList.get(i);
        return res;
    }
}
