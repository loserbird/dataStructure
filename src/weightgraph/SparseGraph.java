package weightgraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * created by bingqin on 2017年10月25日
 * @description 稀疏图  邻接表表示的带权图
 *
 */

public class SparseGraph {
	private int n;//点数
	private int m;//边数
	
	private boolean directed;//是否是有向图
	
	public List<List<Edge>> g;//邻接表
	
	private boolean[] visited; //节点是否访问
	
	public SparseGraph(int n,boolean directed){
		this.n=n;
		this.directed = directed;
		this.m=0;
		g= new ArrayList<List<Edge>>(n);
		for(int i=0;i<n;i++){
			g.add(new ArrayList<Edge>(n));
		}
		visited = new boolean[n];
	}
	
	public int V(){
		return n;
	}
	public int E(){
		return m;
	}
	
	public void addEdge(int v,int w,int weight){
		assert(v>=0 && v<n);
		assert(w>=0 && w<n);
		if(hasEdge(v, w)){
			return;
		}
		Edge edge = new Edge(v, w, weight);
		g.get(v).add(edge);
		//如果不是自环并且是无向图
		if(v!=w && !directed){
			g.get(w).add(new Edge(w, v, weight));
		}
		m++;
	}
	
	public boolean hasEdge(int v ,int w){
		assert(v>=0 && v<n);
		assert(w>=0 && w<n);
		for(int i=0;i<g.get(v).size();i++){
			if(g.get(v).get(i).other(v) == w){
				return true;
			}
		}
		return false;
	}
	
	public void show(){
		for(int i=0;i<n;i++){
			System.out.print(i+": ");
			for(int j=0;j<g.get(i).size();j++){
				System.out.print("("+g.get(i).get(j).other(i) + ":"+g.get(i).get(j).weight+") ");
			}
			System.out.println();
		}
	}
	//深度递归遍历
	public void dfs(int i){
		System.out.print(i+" ");
		visited[i] = true;
		for(int j=0;j<g.get(i).size();j++){
			int v = g.get(i).get(j).other(i);
			if(!visited[v]){
				dfs(v);
			}
		}
	}
	//采用栈深度遍历
	public void stackdfs(int i){
		Stack<Integer> stack = new Stack<Integer>();
		visited[i] = true;
		System.out.print(i+"->");
		stack.push(i);
		while(!stack.isEmpty()){
			int v = stack.peek();
			int num = getUnvisitNode(v);
			if(num == -1){//都遍历过了则把当前节点弹出来
				stack.pop();
			}else{ //否则访问节点并压入栈中
				System.out.print(num+"->");
				visited[num] = true;
				stack.push(num);
			}
		}
		visited = new boolean[n];//便利完再把它初始哈
	}
	//返回一个与节点i相连的并且还没遍历过的节点,如果都已经遍历过了则返回-1
	public int getUnvisitNode(int v){
		for(int i=0;i<g.get(v).size();i++){
			int j = g.get(v).get(i).other(v);
			if(!visited[j]) return j;
		}
		return -1;
	}
	//广度优先遍历
	public void bfs(int i){
		Queue<Integer> queue = new LinkedList<>();
		System.out.print(i+"->");
		visited[i] = true;
		queue.offer(i);
		int v2;
		while(!queue.isEmpty()){
			int v = queue.poll();
			//把所有和v相连的节点访问并入队列中
			while( (v2 = getUnvisitNode(v)) != -1 ){
				System.out.print(v2 + "->");
				visited[v2] = true;
				queue.offer(v2);
			}
		}
		visited = new boolean[n];//便利完再把它初始哈
	}
}
