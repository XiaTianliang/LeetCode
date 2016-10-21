package utils;

import java.util.Arrays;

/**
 * Created by tianliangxia on 16-10-21.
 */
public class Sorts {
    private static final int INSERT_SORT_UM = 8;
    public static void sort(int[] array){
        sort(array, 0, array.length - 1);
    }


    public static void main(String[] args){
        int[] test={9,8,7,4,5,6,2,1,3};
        mergeSort(test,0,test.length-1);
        for(int i:test){
            System.out.print(i+" ");
        }
    }

    public static void sort(int[] array, int beg, int end){
        if((end-beg)<INSERT_SORT_UM){
            insertSort(array, beg, end);
        }else{
            quickSort(array, beg, end);
        }
    }

    //insrt sort
    public static void insertSort(int[] array, int beg, int end){
        for(int i=beg+1;i<=end;i++){
            int tmp = array[i];
            int j=i-1;
            while(j>=beg && array[j]>tmp){  //error mark array[j]>array[j+1]
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=tmp;
        }
    }

    //quick sort
    public static void quickSort(int[] array, int beg, int end){
        if(beg>=end){
            return;
        }
        int mid = beg + (end - beg)/2;
        int target = array[mid];
        array[mid] = array[end];
        int i=beg, j=end;
        while(i<j){
            while (i<j && array[i]<=target)
                i++;
            if(i<j){//error mark
                array[j--] = array[i];
            }
            while (i<j && array[j]>=target)
                j--;
            if(i<j) {
                array[i++]=array[j];
            }
        }
        array[i]=target;
        quickSort(array, beg, i-1);
        quickSort(array, i+1, end);
    }

    //merge sort
    public static void mergeSort(int[] array, int beg, int end){
        if(beg<end){
            int mid = beg + (end - beg)/2;
            mergeSort(array, beg, mid);
            mergeSort(array, mid+1, end);
            merge(array, beg, mid, end);
        }
    }
    private static void merge(int[] array, int beg, int mid, int end){
        int[] left = Arrays.copyOfRange(array, beg, mid+1);
        int[] right = Arrays.copyOfRange(array, mid+1, end+1);
        int i=0,j=0,k=0;
        while (i<left.length && j<right.length){
            if(left[i] < right[j]){
                array[beg+k]=left[i++];
            }else {
                array[beg+k]=right[j++];
            }
            k++;
        }
        while (i<left.length){
            array[beg+k]=left[i++];
            k++;
        }
        while (j<right.length){
            array[beg+k]=right[j++];
            k++;
        }
    }

}
