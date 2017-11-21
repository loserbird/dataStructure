package heap;

import java.util.ArrayList;

public class GeneralMinHeap<T extends Comparable<T>> {
	ArrayList<T> data;
	int count;
	int capacity;
	
	public GeneralMinHeap(int capacity){
		this.capacity = capacity;
		data = new ArrayList<>(capacity+1);//从1开始
		data.add(null);//索引为0的项为null.
		count = 0;
	}
	
	public GeneralMinHeap(T[] arr,int n){
		 capacity = n;
		 data = new ArrayList<>(capacity+1);//从1开始;
		 data.add(null);//索引为0的项为null.
		 

	     for( int i = 0 ; i < n ; i ++ )
	         data.add(arr[i]);
	     count = n;

	     for( int i = count/2 ; i >= 1 ; i -- )
	         shiftDown(i);   
	}
	
	public  void shiftUp(int k){
	        while( k > 1 && data.get(k/2).compareTo(data.get(k)) > 0 ){
	            swap( data,k/2, k );
	            k /= 2;
	        }
	 }

	 public  void shiftDown(int k){
	        while( 2*k <= count ){
	            int j = 2*k;
	            if( j+1 <= count && data.get(j+1).compareTo(data.get(j)) < 0 ) j ++;
	            if( data.get(k).compareTo(data.get(j)) <= 0 ) break;
	            swap( data,k , j );
	            k = j;
	        }
	 }
	 
	 public void insert(T item) {
		 	assert(count+1<=capacity);
			data.set(count+1, item);
			count++;
			shiftUp(count);
	 }
	 
	 public T removeMin(){
	        assert( count > 0 );
	        T ret = data.get(1);
	        swap( data,1,count);
	        count --;
	        shiftDown(1);
	        return ret;
	    }

	 public T getMin(){
	        assert( count > 0 );
	        return data.get(1);
	    }
	 
	 public boolean isEmpty(){
			return count==0;
		}
		
	 public int size(){
			return count;
	 }
	    
	public void swap(ArrayList<T> list,int i,int j){
			T temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
	}
}
