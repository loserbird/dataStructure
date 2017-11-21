package heap;

import java.net.Inet4Address;
import java.util.ArrayList;

/**
 * 
 * @Author bingqin
 * @date 2017年10月26日
 * @description 索引堆 并用reverse数组来优化
 */
public class IndexMinHeap<T extends Comparable<T>> {
	
	ArrayList<T> data;
	int[] indexes;//索引
	int[] reverse;//reverse[i]表示i这个索引在堆中的位置,即indexes[i]=j,reverse[j]=i..
					//indexes[reverse[i]]=i;reverse[indexes[i]]=i;
	int count;
	int capacity;
	
	public IndexMinHeap(T[] arr,int n){
		 capacity = n;
		 data = new ArrayList<>(capacity+1);//从1开始;
		 indexes = new int[capacity+1];
		 reverse = new int[capacity];
		 data.add(null);//索引为0的项为null.

	     for( int i = 0 ; i < n ; i ++ )
	         data.add(arr[i]);
	     count = n;

	     for( int i = count/2 ; i >= 1 ; i -- )
	         shiftDown(i);   
	}
	
	public IndexMinHeap(int capacity){
		this.capacity = capacity;
		indexes = new int[capacity+1];
		reverse = new int[capacity];
		 for( int i = 0 ; i <= capacity ; i ++ )
	            reverse[i] = 0;
		data = new ArrayList<>(capacity+1);//从1开始
		data.add(null);//索引为0的项为null.
		count = 0;
	}
	
	public  void shiftUp(int k){
        while( k > 1 && data.get(indexes[k/2]).compareTo(data.get(indexes[k])) > 0 ){
            swap( indexes,k/2, k );
            reverse[indexes[k/2]] = k/2;
            reverse[indexes[k]] = k;
            k /= 2;
        }
 }

	 public  void shiftDown(int k){
	        while( 2*k <= count ){
	            int j = 2*k;
	            if( j+1 <= count && data.get(j+1).compareTo(data.get(j)) < 0 ) j ++;
	            if( data.get(indexes[k]).compareTo(data.get(indexes[j])) <= 0 ) break;
	            swap( indexes,k , j );
	            reverse[indexes[k]] = k;
	            reverse[indexes[j]] = j;
	            k = j;
	        }
	 }
	 
	 public void insert(int index,T item) {
		 	assert( count + 1 <= capacity );
	        assert( index + 1 >= 1 && index + 1 <= capacity );
	        index += 1;
			data.set(index, item);
			indexes[count+1] = index;
			reverse[index] = count+1;
			count++;
			shiftUp(count);
	 }
	 
	 public T removeMin(){
	        assert( count > 0 );
	        T ret = data.get(indexes[1]);
	        swap( indexes,1,count);
	        reverse[indexes[count]] = 0;
	        reverse[indexes[1]] = 1;
	        count --;
	        shiftDown(1);
	        return ret;
	  }
	 
	 public int removeMinindex(){
		 	assert( count > 0 );

	        int ret = indexes[1] - 1;
	        swap( indexes,1 , count );
	        reverse[indexes[count]] = 0;
	        reverse[indexes[1]] = 1;
	        count--;
	        shiftDown(1);
	        return ret;
	 }
	
	 public T getMin(){
	        assert( count > 0 );
	        return data.get(indexes[1]);
	 }
	 
	public int getMinIndex(){
	        assert( count > 0 );
	        return indexes[1]-1;
	 }
	
	public boolean contain( int index ){
	        return reverse[index+1] != 0;
	 }
	
	public T getItem( int index ){
	        assert( contain(index) );
	        return data.get(index+1);
	}
	
	public void change( int index , T newItem ){

        assert( contain(index) );
        index += 1;
        data.set(index, newItem);

        shiftUp( reverse[index] );
        shiftDown( reverse[index] );
    }
	 
	 public boolean isEmpty(){
			return count==0;
		}
		
	 public int size(){
			return count;
	 }
	    
	 public void swap(int[] arr,int i,int j){
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
	}
}
