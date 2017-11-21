package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Reclutan {
	
	public static void main(String[] args) {
		int[] arr = new int[]{2,1,4,5,1,3,3};
		System.out.println(largestRectangleArea(arr));
	}
	

	
	public static int largestRectangleArea(int[] height){
		Stack<Integer> stack = new Stack<>();
		int result = 0;
		for(int i=0;i<=height.length;i++){
			if(i == height.length){
				while(!stack.isEmpty() ){
					int temp = stack.pop();
					result = Math.max(result, height[temp]*(stack.empty()?i:(i-stack.peek()-1)));
				}
			}
			else if(stack.isEmpty() || height[stack.peek()] <= height[i]){
				stack.push(i);
			}else{
				int temp = stack.pop();
				result = Math.max(result, height[temp]*(stack.empty()?i:(i-stack.peek()-1)));
				--i;
			}
		}
		return result;
	}
}
