package sort;

import static sort.SortUtil.*;

public class InsertSort {
		//插入排序
		public static void insertSort(int[] arr,int n){
			for(int i=1;i<n;i++){
				int p = arr[i];
				int j = i-1;
				while(j>=0 && arr[j]>p){
					arr[j+1] = arr[j];
					j--;
				}
				arr[j+1] = p;
			}
		}
		
		//插入排序简化版
		public static void sort(int arr[],int n){
			for(int i=1;i<n;i++){
				for(int j=i; j>=1 && arr[j]<arr[j-1]; j--){
					SortUtil.swap(arr, j-1, j);
				}
			}
		}
		
		//对arr[l...r]进行插入排序
		public static void insertSort(int[] arr,int l,int r){
			for( int i = l+1 ; i <= r ; i ++ ) {

		        int e = arr[i];
		        int j;
		        for (j = i; j > l && arr[j-1] > e; j--)
		            arr[j] = arr[j-1];
		        arr[j] = e;
		    }
		}
		public static void main(String[] args) {
			int[] arr = generateArr(10, 0, 10);
			sort(arr,10);
			printArr(arr);
		}
}
