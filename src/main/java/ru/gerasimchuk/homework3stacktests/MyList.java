package ru.gerasimchuk.homework3stacktests;

public class MyList<T> {

    class MyNode<T> {
        private T data;
        private MyNode<T> next;

        public MyNode(T data){
            this.data = data;
            next = null;
        }

        public MyNode(){
            this.data = null;
            next = null;
        }

    }

    public int getSize() {
        return size;
    }

    private MyNode<T> head;
    private int size;

    public MyList(){
        head = new MyNode();
        size = 0;
    }

    // добавление в конец
    public boolean add(T data){
        MyNode current = head;
        for(int i = 0; i < size; i++){
            current = current.next;
        }
        current.next = new MyNode(data);
        size++;
        return true;
    }
    // добавление элемента на место элемента с индексом pos
    public boolean add(T data, int pos){
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        MyNode addingNode = new MyNode(data);
        if (pos == 0){
            MyNode tmp = head.next;
            head.next = addingNode;
            addingNode.next = tmp;
            size++;
            return true;
        }
        else {
            MyNode tmp = head.next;
            MyNode previous = head;
            for(int i = 0; i < pos; i++){
                previous = tmp;
                tmp = tmp.next;
            }
            MyNode insert = new MyNode(data);
            previous.next = insert;
            insert.next = tmp;
            size++;
            return true;
        }
    }

    public T get(int pos){
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        MyNode currentNode = head.next;
        for(int i = 0; i < pos; i++){
            currentNode = currentNode.next;
        }
        return (T) currentNode.data;
    }

    // удаление элемента с позиции pos

    public boolean remove(int pos){
        if (pos < 0 || pos >=size) return false;
        if (size == 0) return false;

        MyNode currentNode = head;
        for(int i = 0; i < pos; i++){
            currentNode = currentNode.next;
        }
        currentNode.next = currentNode.next.next;
        size--;
        return true;
    }

    boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }

    @Override
    public String toString() {
        if (size == 0) return "empty";
        StringBuilder sb = new StringBuilder("");
        MyNode currentNode = head;
        for(int i = 0; i < size; i++){
            currentNode=currentNode.next;
            sb.append(currentNode.data + " ");
        }
        return sb.toString();
    }
}
