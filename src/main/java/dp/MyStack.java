package dp;


import java.util.LinkedList;

/**
 * Created by tianliangxia on 16-10-23.
 */
public class MyStack<T> extends LinkedList<T> implements  MyStackInterface<T>{
    public MyStack(){
        super();
    }
    public void push(T t){
        addLast(t);
    }
    public T top(){
        return getLast();
    }
    public T pop(){
        return removeLast();
    }
    public int size(){
        return super.size();
    }
    public boolean isEmpty(){
        return super.isEmpty();
    }
}
