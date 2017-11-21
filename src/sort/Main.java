package sort;

import static sort.SortUtil.*;

public class Main {
	
	
	
	//选择排序
	public static void selectsort(int[] arr,int n){
		for(int i=0;i<n-1;i++){
		   for(int j=i+1;j<n;j++){
			   if(arr[i]>arr[j]){
				   int temp = arr[j];
				   arr[j]=arr[i];
				   arr[i]=temp;
			   }
		   }
		}
	}
	//冒泡排序
	public static void bubblesort(int[] arr,int n){
		for(int i=0;i<n-1;i++){
			for(int j=0;j<n-i-1;j++){
				if(arr[j]>arr[j+1]){
					int temp = arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=temp;
				}
			}
		}
	}
	
	
	public static void merge(int[] arr,int l,int m,int r){
		int[] temp = new int[r-l+1];
		for(int i=l;i<=r;i++){
			temp[i-l] = arr[i];
		}
		int i = l;
		int k = l;
		int j = m+1;
		while(i<=m && j<=r){
			if(temp[i-l] <= temp[j-l]){
				arr[k++] = temp[i-l];
				i++;
			}else{
				arr[k++] = temp[j-l];
				j++;
			}
		}
		while(i<=m){
			arr[k++] = temp[i-l];
			i++;
		}
		while(j<=r){
			arr[k++] = temp[j-l];
			j++;
		}
	}
	//合并排序
	public static void mergeSort(int arr[],int l,int r){
		if(l>=r) return;
		int m = (l+r)/2;
		mergeSort(arr, l, m);
		mergeSort(arr, m+1, r);
		if(arr[m] > arr[m+1]){
			merge(arr, l, m, r);
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		//int[] arr = generateArr(10, 0, 10);
		//printArr(arr);
		//selectsort(arr,10);
		//bubblesort(arr,10);
		//printArr(arr);
		int[] arr = new int[]{1,3,5,7,2,4,6,8};
		merge(arr,0,3,7);
		for(int item : arr){
			System.out.print(item+" ");
		}
	}
	
}
