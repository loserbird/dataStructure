package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import stack.LLStack;

/**
 * 
 * created by bingqin on 2017年8月3日
 * @description 二叉树
 *
 */
public class BinaryTreeNode {
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	
	public BinaryTreeNode(){}
	
	public BinaryTreeNode(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	//前序遍历
	static void preOrder(BinaryTreeNode root){
		if(root != null){
			System.out.println(root.data);
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}
	//
	void PreOrderNonRecursive(BinaryTreeNode root){
		if(root == null) return;
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		while(true){
			while(root != null){
				System.out.println(root.data);
				stack.push(root);
				root = root.getLeft();
			}
			if(stack.isEmpty()) break;
			root = (BinaryTreeNode) stack.pop();
			root = root.getRight();
		}
	}
	//非递归后续遍历，当从栈中出栈一个元素时检查这个元素与栈顶元素的右节点是否相同
	public void postOrderNonRecursive(BinaryTreeNode root){
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		while(true){
			if(root != null){
				stack.push(root);
				root = root.getLeft();
			}else{
				if(stack.isEmpty()){
					System.out.println("stack is empty");
					return;
				}else{
					if(stack.peek().getRight() == null){
						root = stack.pop();
						System.out.println(root.getData());
						if(stack.peek().getRight() == root){
							System.out.println(stack.peek().getData());
							stack.pop();
						}
					}
					if(!stack.isEmpty()) root = stack.peek().getRight();
					else root = null;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	
	
	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		BinaryTreeNode left = new BinaryTreeNode(2);
		BinaryTreeNode right = new BinaryTreeNode(3);
		root.setLeft(left);
		root.setRight(right);
		left.setLeft(new BinaryTreeNode(4));
		right.setLeft(new BinaryTreeNode(5));
		right.setRight(new BinaryTreeNode(6));
		
		ArrayList<String> list = new ArrayList<>();
		preOrder(root,list);
		for(String str : list){
			System.out.print(str+" ");
		}
		System.out.println("------------");
		BinaryTreeNode newroot = deserialize(list);
		preOrder(newroot);
	}
	
	public static void preOrder(BinaryTreeNode node,ArrayList<String> list){
		if(node != null){
			list.add(node.getData()+"!");
			preOrder(node.getLeft(), list);
			preOrder(node.getRight(), list);
		}else{
			list.add("#!");
		}
	}
	
	public static BinaryTreeNode deserialize(ArrayList<String> list){
		if(list == null || list.size() == 0){
			return null;
		}
		String strdata = list.get(0);
		list.remove(0);
		if(strdata.equals("#!")){
			return null;
		}
		int data = Integer.valueOf(strdata.substring(0, strdata.indexOf('!')));
		BinaryTreeNode node = new BinaryTreeNode(data);
		node.setLeft(deserialize(list));
		node.setRight(deserialize(list));
		return node;
	}
}
