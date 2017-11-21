package stack;

import java.util.Stack;

public class ReverseStack {
	
	
	public static int getAndRemoveBottomEle(Stack<Integer> stack){
		int result = stack.pop();
		if(stack.isEmpty()){
			return result;
		}else {
			int last = getAndRemoveBottomEle(stack);
			stack.push(result);
			return last;
		}
		
	}
	
	public static void reverse(Stack<Integer> stack){
		if(stack.empty()){
			return;
		}
		int i = getAndRemoveBottomEle(stack);
		reverse(stack);
		stack.push(i);
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack);
		reverse(stack);
		System.out.println(stack);
	}
}
