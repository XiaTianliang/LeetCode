package utils;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class ArrayUtils {
    public static int indexOf(int[] array, int beg, int end, int key){
        if(beg>end){
            throw new IndexOutOfBoundsException();
        }
        for(int i=beg;i<end;i++){
            if(array[i] == key)
                return i;
        }
        return -1;
    }

    public static int indexOf(int[] array, int key){
        return indexOf(array, 0, array.length, key);
    }
}
