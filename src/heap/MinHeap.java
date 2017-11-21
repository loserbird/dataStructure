package heap;

public class MinHeap {
	int[] data;
	int count;
	int capacity;
	
	public MinHeap(int capacity){
		this.capacity = capacity;
		data = new int[capacity+1];//从1开始
		count = 0;
	}
	
	public MinHeap(int[] arr,int n){
		 data = new int[n+1];
		 capacity = n;

	     for( int i = 0 ; i < n ; i ++ )
	         data[i+1] = arr[i];
	     count = n;

	     for( int i = count/2 ; i >= 1 ; i -- )
	         shiftDown(i);   
	}
	
	public  void shiftUp(int k){
	        while( k > 1 && data[k/2] > data[k] ){
	            swap( data,k/2, k );
	            k /= 2;
	        }
	 }

	 public  void shiftDown(int k){
	        while( 2*k <= count ){
	            int j = 2*k;
	            if( j+1 <= count && data[j+1] < data[j] ) j ++;
	            if( data[k] <= data[j] ) break;
	            swap( data,k , j );
	            k = j;
	        }
	 }
	 
	 public void insert(int item) throws Exception{
			if(count+1>capacity){
				throw new Exception("can not over capacity");
			}
			data[count+1] = item;
			count++;
			shiftUp(count);
	 }
	 
	 public int removeMin(){
	        assert( count > 0 );
	        int ret = data[1];
	        swap( data,1,count);
	        count --;
	        shiftDown(1);
	        return ret;
	    }

	 public int getMin(){
	        assert( count > 0 );
	        return data[1];
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
