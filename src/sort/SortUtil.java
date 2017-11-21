package sort;

import java.util.Random;

public class SortUtil {
	public static int[] generateArr(int n,int left,int right){
		Random random = new Random();
		int[] arr = new int[n];
		for(int i=0;i<n;i++){
			arr[i]= random.nextInt(right-left+1)+left;
		}
		return arr;
	}
	
	public static void printArr(int[] arr){
		for(int i : arr){
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	//交换arr[i],arr[j]的数据
	public static void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}	
}
