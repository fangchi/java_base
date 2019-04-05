package com.fc.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String []args){
        int []arr = {7,6,5,9,3,2,8,4,1};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    
    
    public static int[] sort(int[] array, int left, int right) {

        if (left == right) {

           return new int[] { array[left] };

        }

        int mid = (right + left) / 2;

        int[] l = sort(array, left, mid);

        int[] r = sort(array, mid + 1, right);

        return merge(l, r);

    }
    
    
    public static int[] merge(int[] l, int[] r) {

        int[] result = new int[l.length + r.length];

        int p = 0;

        int lp = 0;

        int rp = 0;

        while (lp < l.length && rp < r.length) {

           result[p++] = l[lp] < r[rp] ? l[lp++] : r[rp++];

        }

        while (lp < l.length) {

           result[p++] = l[lp++];

        }

        while (rp < r.length) {

           result[p++] = r[rp++];

        }

        return result;

    }
}