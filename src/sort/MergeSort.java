package sort;

public class MergeSort {
	
	//采用迭代自底向上归并
	public static void mergeSortBU(int[] arr,int n){
		for(int step=1;step<=n;step+=step){
			for(int i=0;i+step<n;i+=step+step){
				merge(arr, i, i+step-1, Math.min(i+step+step-1,n-1));
			}
		}
	}
	//采用递归自顶向下归并排序
	//当n<16时，可以使用插入排序，效率会快点
	public static void mergeSort(int[] arr,int n){
		mergeSort(arr,0,n-1);
	}
	//递归调用mergeSort，对arr[left...right]进行归并排序
	private static void mergeSort(int[] arr,int left,int right){
		if(left >=right){	//说明已经剩下最后一个元素
			return ;
		}
		int middle = (left+right)/2;
		mergeSort(arr, left, middle);//对左半部分进行归并排序
		mergeSort(arr, middle+1, right);//对右半部分进行归并排序
		//优化,判断是否需要合并。如果左半部分的最后一个元素大于右半部分的第一个元素才需要合并
		if(arr[middle] > arr[middle+1])
		merge(arr, left, middle, right);//合并
	}
	//将排好序的arr[left....middle]和[middle+1...right]合并
	public static void merge(int[] arr,int left,int middle,int right){
		int[] help = new int[right-left+1];//使用一个辅助数组存放arr[left...right]数据
		for(int i=left;i<=right;i++){
			help[i-left] = arr[i];
		}
		//下面就是将两个有序数组合并到一个数组的操作。即将help数组放置到arr[left...right]中
		int i=left,j=middle+1;	//两个小数组的起始边界
		for(int k=left;k<=right;k++){
			if(i>middle){	//如果i超过了边界，说明arr[left....middle]已经被放置完毕
				arr[k] = help[j-left];
				j++;
			}else if(j>right){   ////如果j超过了边界，说明已经被放置完毕
				arr[k] = help[i-left];
				i++;
			}
			else if(help[i-left]<help[j-left]){
				arr[k]=help[i-left];
				i++;
			}else{
				arr[k] = help[j-left];
				j++;
			}
		}
	}
	
	public static int mergeA(int[] arr,int left,int middle,int right){
		int count=0;
		int[] help = new int[right-left+1];//使用一个辅助数组存放arr[left...right]数据
		for(int i=left;i<=right;i++){
			help[i-left] = arr[i];
		}
		int i=left,j=middle+1;
		for(int k=left;k<=right;k++){
			if(i>middle){	//如果i超过了边界，说明arr[left....middle]已经被放置完毕
				arr[k] = help[j-left];
				j++;
			}else if(j>right){   ////如果j超过了边界，说明已经被放置完毕
				arr[k] = help[i-left];
				i++;
			}
			else if(help[i-left]<help[j-left]){
				arr[k]=help[i-left];
				i++;
			}else{
				arr[k] = help[j-left];
				j++;
				count=count+middle-i+1;
			}
		}
		return count;
	}
}
