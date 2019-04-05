package com.fc.sort;


public class QuickSort {

	
	public static void quickSort(int[] array,int low,int high){
		if(low!=high){
			int svit = getSvit(array,low,high);
			quickSort(array,low,svit);
			if(svit < array.length-1){
				quickSort(array,svit+1,high);
			}
		}
	}
	
	
	public static int getSvit(int[] array,int low,int high){
		int svitp = array[low];
		int direct  = 1;  //0: front 1 :back
		while(high > low){
			if(direct == 1){ //back
				int current = array[high];
				if(current < svitp){
					array[low] =current;
					low++;
					direct = 0 ;
					continue;
				}else{
					high--;
				}
			}
			if(direct == 0){ //front
				int current = array[low];
				if(current > svitp){
					array[high] =current;
					high--;
					direct = 1 ;
					continue;
				}else{
					low++;
				}
			}
		}
		array[low] = svitp;
		return high;
	}
	
	public static void main(String[] args) {
		int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }
	}
}
