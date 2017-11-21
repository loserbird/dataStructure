package heap;

import java.util.Random;

public class MaxHeap {
	
	
	int[] data;
	int count;
	int capacity;
	
	public MaxHeap(int capacity){
		this.capacity = capacity;
		data = new int[capacity+1];//从1开始
		count = 0;
	}
	
	public MaxHeap(int[] arr,int n){
		data = new int[n+1];
		capacity = n;
		for(int i=0;i<n;i++){
			data[i+1] = arr[i];
		}
		count = n;
		for(int i=count/2;i>=1;i--){
			shiftDown(i);
		}
	}
	
	public boolean isEmpty(){
		return count==0;
	}
	
	public int size(){
		return count;
	}
	
	public void insert(int item) throws Exception{
		if(count+1>capacity){
			throw new Exception("can not over capacity");
		}
		data[count+1] = item;
		count++;
		shiftUp(count);
	}
	
	public void shiftUp(int k){
		while(k>1 && data[k]>data[k/2]){
			swap(data, k, k/2);
			k/=2;
		}
	}
	//删除最大值并返回最大值
	public int removeMax(){
		assert(count>0);
		int max = data[1];
		swap(data, 1, count);
		count--;
		shiftDown(1);
		return max;
	}
	
	public int getMax(){
		assert(count >0);
		return data[1];
	}
	
	public void shiftDown(int k){
		//只要k有左孩子
		while(2*k <=count){
			int j=2*k;
			if(j+1 <=count && data[j+1] > data[j]){ //如果有右孩子并且大于左孩子
				j+=1;
			}
			if(data[k] >= data[j]) break;
			swap(data, k, j);
			k=j;
		}
	}
	
	public void heapSort(int[] arr) throws Exception{
		int len = arr.length;
		MaxHeap heap = new MaxHeap(len);
		for(int i=0;i<len;i++){
			heap.insert(arr[i]);
		}
		for(int i=len-1;i>=0;i--){
			arr[i] = heap.removeMax();
		}
	}
	//将n个元素逐个插入到一个空堆中，时间复杂度为O(nlogn),而heapify的为O（n)
	public void heapSort2(int[] arr){
		int len = arr.length;
		MaxHeap heap = new MaxHeap(arr,len);
		for(int i=len-1;i>=0;i--){
			arr[i] = heap.removeMax();
		}
	}
	//原地堆排序，将原数组看成堆
	public void heapSort3(int[] arr,int n){
		//heapify
		for(int i=(n-1)/2;i>=0;i--){
			shiftdown(arr, n, i);
		}
		
		for(int i=n-1;i>0;i--){
			swap(arr, 0, i);
			shiftdown(arr, i, 0);
		}
	}
	//将堆arr[0...n-1]的arr[k]shiftdown.以0为开始索引
	private void shiftdown(int[] arr,int n,int k){
		while(2*k+1 <n){
			int j = 2*k+1;
			if(j+1<n && arr[j+1]>arr[j]){
				j += 1;
			}
			if(arr[k] >= arr[j]) break;
			swap(arr, k, j);
			k=j;
		}
	}
	
	public void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) throws Exception {
		MaxHeap heap = new MaxHeap(100);
		
		Random random = new Random();
		for(int i=0;i<15;i++){
			heap.insert(random.nextInt(100));
		}
		while(!heap.isEmpty()){
			System.out.println(heap.removeMax());
		}
		
	}
}
