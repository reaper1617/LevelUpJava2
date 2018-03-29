package ru.gerasimchuk.homework3stacktests;



public class MyStack<T> {

    private MyList<T> list;

    public MyStack(){
        list = new MyList<>();
    }

    boolean push (T data){
        if (data==null) return false;
        list.add(data);
        return true;
    }

    T pop() throws EmptyStackException {
        if (list.isEmpty()) throw new EmptyStackException();
        T result = list.get(list.getSize()-1);
        list.remove(list.getSize()-1);
        return result;
    }

    T peek() throws EmptyStackException{
        if (list.isEmpty()) throw new EmptyStackException();
        T result = list.get(list.getSize()-1);
        return result;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
