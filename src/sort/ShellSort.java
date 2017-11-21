package sort;

import static sort.SortUtil.generateArr;
import static sort.SortUtil.printArr;

public class ShellSort {
	
	//简化版的希尔排序
	public static void sort(int[] arr , int n){
		for(int gap = n/2;gap>=1;gap/=2){
			for(int i=gap;i<n;i++){
				for(int j=i-gap;j>=0 && arr[j+gap]<arr[j];j-=gap){//插入排序
					SortUtil.swap(arr, j, j+gap);
				}
			}
		}
	}
	
	void shellsort3(int a[], int n)  
	{  
	    int j, gap;  
	      
	    for (gap = n / 2; gap > 0; gap /= 2)  
	        for (j = gap; j < n; j++)//从数组第gap个元素开始  
	            if (a[j] < a[j - gap])//每个元素与自己组内的数据进行直接插入排序  
	            {  
	                int temp = a[j];  
	                int k = j - gap;  
	                while (k >= 0 && a[k] > temp)  
	                {  
	                    a[k + gap] = a[k];  
	                    k -= gap;  
	                }  
	                a[k + gap] = temp;  
	            }  
	} 
	
	public static void sort2(int[] arr){
		int length = arr.length;
		int i,j,k,gap,key;	//gap为步长，也为组数。
		for(gap=length/2;gap>0;gap/=2){
			
			for(i=0;i<gap;i++){
				for(j=i+gap;j<length;j+=gap){
					key = arr[j];
					k = j-gap;
					while(k>=0&&arr[k]>key){
						arr[k+gap] = arr[k];
						k-=gap;
					}
					arr[k+gap] = key;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = generateArr(10, 0, 10);
		printArr(arr);
		System.out.println();
		sort(arr,10);
		printArr(arr);
	}
}
