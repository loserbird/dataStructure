package sort;

import java.util.ArrayList;
import java.util.Collections;

public class RadixSort {
	
	//计数排序
	public static int[] countSort(int[] arr){
	    if (arr == null || arr.length == 0) {
	        return null;
	    }
	    
	    int max = Integer.MIN_VALUE;
	    int min = Integer.MAX_VALUE;
	    
	    //找出数组中的最大最小值
	    for(int i = 0; i < arr.length; i++){
	        max = Math.max(max, arr[i]);
	        min = Math.min(min, arr[i]);
	    }
	    
	    int help[] = new int[max];
	    
	    //找出每个数字出现的次数
	    for(int i = 0; i < arr.length; i++){
	        int mapPos = arr[i] - min;
	        help[mapPos]++;
	    }
	    
	    int index = 0;
	    for(int i = 0; i < help.length; i++){
	        while(help[i]-- > 0){
	            arr[index++] = i+min;
	        }
	    }
	    
	    return arr;
	}
	//桶排序
	public static void bucketSort(int[] arr){
	    
	    int max = Integer.MIN_VALUE;
	    int min = Integer.MAX_VALUE;
	    for(int i = 0; i < arr.length; i++){
	        max = Math.max(max, arr[i]);
	        min = Math.min(min, arr[i]);
	    }
	    
	    //桶数
	    int bucketNum = (max - min) / arr.length + 1;
	    ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
	    for(int i = 0; i < bucketNum; i++){
	        bucketArr.add(new ArrayList<Integer>());
	    }
	    
	    //将每个元素放入桶
	    for(int i = 0; i < arr.length; i++){
	        int num = (arr[i] - min) / (arr.length);
	        bucketArr.get(num).add(arr[i]);
	    }
	    
	    //对每个桶进行排序
	    for(int i = 0; i < bucketArr.size(); i++){
	        Collections.sort(bucketArr.get(i));
	    }
	    System.out.println(bucketArr.toString());
	    
	}
	
		//基数排序函数
		//a表示要排序的数组
		//d表示每一位数字的范围（这里是10进制数，有0~9一共10种情况）
		public static void sort(int[] a,int d){
			//n用来表示当前排序的是第几位
			int n = 1;
			//hasNum用来表示数组中是否有至少一个数字存在第n位
			boolean hasNum = false;
			//二维数组temp用来保存当前排序的数字
			//第一维d表示一共有d个桶
			//第二维a.length表示每个桶最多可能存放a.length个数字
			int[][] temp = new int[d][a.length];
			int[] order = new int[d];
			while(true){
				//判断是否所有元素均无比更高位，因为第一遍一定要先排序一次，所以有n!=1的判断
				if(n != 1 && !hasNum){
					break;
				}
				hasNum = false;
				//遍历要排序的数组，将其存入temp数组中（按照第n位上的数字将数字放入桶中）
				for(int i = 0;i < a.length;i++){
					int x = a[i]/(n*10);
					if(x != 0){
						hasNum = true;
					}
					int lsd = (x%10);
					temp[lsd][order[lsd]] = a[i];
					order[lsd]++;
				}
				//k用来将排序好的temp数组存入data数组（将桶中的数字倒出）
				int k = 0;
				for(int i = 0;i < d;i++){
					if(order[i] != 0){
						for(int j = 0;j < order[i];j++){
							a[k] = temp[i][j];
							k++;
						}				    
					}
					order[i] = 0;
				}
				n++;
			}
		}
}
