package dp;

/**
 * Created by tianliangxia on 16-10-23.
 */
public interface MyStackInterface<T> {
    void push(T t);
    T top();
    T pop();
    int size();
    boolean isEmpty();
}
