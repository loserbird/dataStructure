package stack;
/**
 * 
 * created by bingqin on 2017年8月3日
 * @description 基于链表实现的栈 在链表的表头插入元素实现push操作，删除表头节点实现出栈
 *
 */
public class LLStack {
	private LLNode headNode;
	
	public LLStack(){
		this.headNode = new LLNode(0);
	}
	
	public void push(int data){
		if(headNode == null){
			headNode = new LLNode(data);
		}else if(headNode.getData() == 0){
			headNode.setData(data);
		}else{
			LLNode llNode = new LLNode(data);
			llNode.setNext(headNode);
			headNode = llNode;
		}
	}
	public int pop(){
		if(headNode == null){
			System.out.println("stack empty");
			return -1;
		}else{
			int data = headNode.getData();
			headNode = headNode.next;
			return data;
		}
	}
	
	public boolean isEmpty(){
		if(headNode == null){
			return true;
		}
		return false;
	}
	
	
	
	
	class LLNode{
		private int data;
		private LLNode next;
		
		public LLNode(int data){
			this.data = data;
		}
		
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public LLNode getNext() {
			return next;
		}
		public void setNext(LLNode next) {
			this.next = next;
		}
		
		
	}
}
