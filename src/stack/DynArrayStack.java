package stack;
/**
 * 
 * created by bingqin on 2017年8月3日
 * @description 基于动态数组实现
 *
 */
public class DynArrayStack {
	private int top;
	
	private int capacity;
	
	private int[] array;
	
	public DynArrayStack(){
		capacity = 1;
		array = new int[capacity];
		top = -1;
	}
	public boolean isEmpty(){
		return top == -1;
	}
	public boolean isStackFull(){
		return (top == capacity-1);
	}
	public void push(int data){
		if(isStackFull()) doubleStack();
		array[++top] = data;
	}
	private void doubleStack() {
		int newArray[] = new int[capacity *2];
		System.arraycopy(array, 0, newArray, 0, capacity);
		capacity = capacity *2;
		array = newArray;
		
	}
	public int pop(){
		if(isEmpty()){
			System.out.println("stack is empty");
			return 0;
		}else{
			return array[top--];
		}
	}
	public void deleteStack(){
		top = -1;
	}
}
