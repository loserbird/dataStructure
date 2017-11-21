package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * 
 * created by bingqin on 2017年10月23日
 * @description 二叉查找树
 *
 */
public class BinarySearchTree<K extends Comparable<K>,V> {
	
	int count;
	
	Node<K, V> root;
	
	class Node<K extends Comparable<K>,V>{
		K key;
		V value;
		Node<K,V> left;
		Node<K,V>  right;
		
		public Node(K k,V v){
			this.key = k;
			this.value = v;
			this.left = null;
			this.right = null;
		}
	}
	
	public BinarySearchTree(K k,V v){
		Node<K,V> node = new Node<K,V>(k, v);
		this.root = node;
		this.count=1;
	}
	public BinarySearchTree(){
		this.count = 0;
		this.root = null;
	}
	
	public int size(){
		return count;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	//插入操作
	public void insert(K key,V value){
		root = insert(root, key, value);
	}
	//往以root为根节点的树中插入节点，并返回根节点
	private Node<K,V> insert(Node<K,V> root,K key,V value){
		if(root == null){
			count++;
			return new Node<K,V>(key, value);
		}
		
		//如果相等，修改值即可返回
		if(root.key.compareTo(key) == 0){
			root.value = value;
		}else if(root.key.compareTo(key)>0){
			root.left = insert(root.left, key, value);
		}else{
			root.right = insert(root.right, key, value);
		}
		return root;
	}
	
	public boolean contains(K key){
		return contains(root,key);
	}
	
	private boolean contains(Node<K, V> root, K key) {
		if(root == null){
			return false;
		}
		if(root.key.compareTo(key) == 0){
			return true;
		}else if(root.key.compareTo(key) >0){
			return contains(root.left,key);
		}else {
			return contains(root.right, key);
		}
	}
	public V search(K key){
		return search(root,key);
	}
	//往以root为根节点的树中搜索key，并返回对应的value
	private V search(Node<K,V> root,K key){
		if(root == null) return null;
		if(root.key.compareTo(key) == 0){
			return root.value;
		}else if(root.key.compareTo(key) > 0 ){
			return search(root.left,key);
		}else{
			return search(root.right, key);
		}
	}
	//前序遍历
	public void preOrder(){
		preOrder(root);
	}
	public void inOrder(){
		inOrder(root);
	}
	public void postOrder(){
		postOrder(root);
	}
	
	private void preOrder(Node<K,V> root){
		if(root != null){
			System.out.print(root.key+" ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	private void inOrder(Node<K,V> root){
		if(root != null){
			inOrder(root.left);
			System.out.print(root.key+" ");
			inOrder(root.right);
		}
	}
	private void postOrder(Node<K,V> root){
		if(root != null){
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.key+" ");
		}
	}
	//层次遍历
	public void levelOrder(){
		Queue<Node<K,V>> queue = new LinkedList<>();
		Node<K,V> node = null;
		queue.offer(root);
		while(!queue.isEmpty()){
			node = queue.poll();
			System.out.print(node.key+" ");
			if(node.left != null){
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	//寻找最小K值
	public K minimum(){
		assert(count != 0);
		return minimum(root).key;
	}
	//在以node为根的二叉搜索树中查找最小值
	private Node<K, V> minimum(Node<K, V> node){
		if(node.left == null){
			return node;
		}
		return minimum(node.left);
	}
	//删除掉最小节点
	public void removeMin(){
		if(root != null) {
			removeMin(root);
		}
	}
	//删除掉以node为根的二分搜索树中的最小节点
	//返回删除节点后新的根
	public Node<K, V> removeMin(Node<K, V> node){
		if(node.left == null) {
			Node<K, V> right = node.right;
			count--;
			node = null;//让垃圾回收器回收
			return right;
		}
		node.left = removeMin(node.left);
		return node;
	}
	//任意删除一个节点
	public void remove(K key){
		root = remove(root,key);
	}
	//删除以root为根节点的二叉搜索树的以key为键的节点，并返回删除后树的根节点
	private Node<K, V> remove(Node<K, V> root,K key){
		if(root == null) return null;
		if(root.key.compareTo(key)>0){
			root.left = remove(root.left, key);
			return root;
		}else if(root.key.compareTo(key) <0){
			root.right = remove(root.right, key);
			return root;
		}else{ //root.key == key
			if(root.left == null) {
				Node<K, V> right = root.right;
				root = null;
				count--;
				return right;
			}
			if(root.right == null){
				Node<K, V> left = root.left;
				root = null;
				count--;
				return left;
			}
			//如果root的左右孩子皆不为空,
			//可以用root的前驱(root左子树中最大的元素）或者后继（root右子树中最小的元素）去取代root.
			Node<K, V> successor = minimum(root.right);
			successor.right = removeMin(root.right);
			successor.left = root.left;
			root = null;//删除root
			return successor;
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer,Integer> tree = new BinarySearchTree<Integer,Integer>();
		Random random = new Random();
		for(int i=0;i<10;i++){
			int key = random.nextInt(100);
			tree.insert(key, key);
		}
		tree.inOrder();
		System.out.println();
		tree.levelOrder();
		tree.remove(tree.root.right.key);
		System.out.println();
		tree.levelOrder();
		System.out.println();
		tree.inOrder();
	}
	
}
