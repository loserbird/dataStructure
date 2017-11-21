package stack;
/**
 * 
 * created by bingqin on 2017年8月3日
 * @description 基于数组实现的栈
 *
 */
public class ArrayStack {
	private int top;//记录数组当前栈顶元素的下标
	
	private int capacity;
	
	private int[] array;
	
	public ArrayStack(){
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
		if(isStackFull()) System.out.println("stack overflow");
		else array[++top] = data;
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
