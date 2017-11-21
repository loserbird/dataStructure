package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
	Queue<Integer> queue1 ;
	Queue<Integer> queue2;
	
	public StackWithTwoQueues(){
		queue1 = new ArrayDeque<>();
		queue2 = new ArrayDeque<>();
	}
	
	public void push(Integer data){
		if(queue1.isEmpty()){
			queue2.add(data);
		}else{
			queue1.add(data);
		}
	}
	public int pop(){
		int i,size;
		if(queue2.isEmpty()){
			size = queue1.size();
			i = 0;
			while(i<size-1){
				queue2.add(queue1.poll());
				i++;
			}
			return queue1.poll();
		}else{
			size = queue2.size();
			i = 0;
			while(i<size-1){
				queue1.add(queue2.poll());
				i++;
			}
			return queue2.poll();
		}
	}
}
