package sort;

import java.util.Random;
import static sort.SortUtil.*;
public class QuickSort {
		static Random random = new Random();
	
		//快速排序
		//当数组近乎有序或有大量重复元素时，时间复杂度退化为O(n的平方)
		public static void quicksort1(int[] arr,int n){
			quicksort1(arr,0,n-1);
		}
		//递归调用快速排序
		private static void quicksort1(int[] arr,int l,int r){
			if(l>=r) return;
			int p = partition1(arr, l, r);
			quicksort1(arr, l,p-1);
			quicksort1(arr, p+1,r);
		}
		//快速排序，将arr[l]放到最终排序正确的位置上。
		//返回p，使得arr[l..p-1]<arr[p],arr[p+1...r]>arr[p]
		public static int partition1(int[] arr,int l,int r){
			int v = arr[l];
			
			int j = l;
			for(int i=l+1;i<=r;i++){
				if(arr[i]<v){
					swap(arr, i, j+1);
					j++;
				}
			}
			//交换arr[l],arr[j]
			swap(arr, l, j);
			return j;
		}
		public static void quicksort2(int[] arr,int n){
			quicksort2(arr,0,n-1);
		}
		//递归调用快速排序
		private static void quicksort2(int[] arr,int l,int r){
			if(l>=r) return;
			int p = partition2(arr, l, r);
			quicksort2(arr, l,p-1);
			quicksort2(arr, p+1,r);
		}
		public static int partition2(int[] arr,int l,int r){
			int v = arr[l];
			// arr[l+1...i) <= v; arr(j...r] >= v
			int i=l+1,j=r;
			while(true){
				while(i<=r && arr[i]<v) i++;
				while(j>=l+1 && arr[j]>v) j--;
				if(i>j) break;
				swap(arr, i, j);
				i++;
				j--;
			}
			swap(arr,l,j);
			return j;
		}
	
	
	//使用三路快速排序，避免重复元素过多而导致时间复杂度为O(2^n)的情况
	public static void quicksort3way(int[] arr, int l,int r){
		if(l>=r) return;
		//当排序的数目较小时使用插入排序可提高性能
		if(r-l <= 15){
			InsertSort.insertSort(arr, l,r);
		}
		//随机选取基准,避免数组近乎有序而导致时间复杂度为O(2^n)的情况
		int temp = random.nextInt(r-l+1)+l;
		SortUtil.swap(arr, l, temp);
		
		int v = arr[l];
		int lt = l; //arr[l+1...lt]<v
		int gt = r+1;//arr[gt...r]>v
		int i = l+1;//arr[lt+1..i)==v
		
		while(i<gt){
			if(arr[i]<v){
				SortUtil.swap(arr,i,lt+1);
				i++;
				lt++;
			}else if(arr[i]>v){
				SortUtil.swap(arr,i,gt-1);
				gt--;
			}else{
				i++;
			}
		}
		SortUtil.swap(arr,l,lt);
		quicksort3way(arr,l,lt-1);
		quicksort3way(arr,gt,r);
	}
	
	public static void quicksort3(int[] arr,int n){
		quicksort3way(arr,0,n-1);
	}
	
	public static void main(String[] args) {
		int[] arr = generateArr(10, 0, 10);
		printArr(arr);
		quicksort3(arr, 10);
		printArr(arr);
	}
}
