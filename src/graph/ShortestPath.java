package graph;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

/**
 * 
 * @Author bingqin
 * @date 2017年10月25日
 * @description 求无权图中源点s到图中其它节点的最短路径
 */
public class ShortestPath {
	private SparseGraph graph;
	
	private int[] from;//from[i]表示i节点是从哪个遍历来的
	
	private boolean[] visited;
	
	private int s;//原点s
	
	private int[] shortest;//储存各节点路径的长度
	
	public ShortestPath(SparseGraph graph,int s){
		this.graph = graph;
		from = new int[graph.V()];
		shortest = new int[graph.V()];
		for(int i=0;i<graph.V();i++){
			from[i] = -1;
			shortest[i] = -1;
		}
		visited = new boolean[graph.V()];
		this.s = s;
		
		Queue<Integer> queue = new LinkedList<>();
		
		 // 利用bfs无向图最短路径算法
		 queue.offer(s);
		 visited[s] = true;
		 shortest[s] = 0;
		 System.out.println("广度优先遍历的过程：");
		 while(!queue.isEmpty()){
			 int v = queue.poll();
			 System.out.print(v+"->");
			 for(int i=0;i<graph.g.get(v).size();i++){
				 int j = graph.g.get(v).get(i);
				 if(!visited[j]){
					 queue.offer(j);
					 visited[j] = true;
					 from[j] = v;
					 shortest[j] = shortest[v]+1;
				 }
			 }
		 }
	}
	
	
	//s到w有没有路径
	public boolean hasEdge(int w){
		assert(w>=0 && w<graph.V());
		return visited[w];//如果为true则表明从s进行深度遍历的时候有遍历过他
	}
	//s到w的路径
	public void showPath(int w){
		Stack<Integer> stack = new Stack<>();
		int p = w;
		while(p != -1){
			stack.push(p);
			p = from[p];
		}
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+"->");
		}
	}
	
	public int length(int w){
		return shortest[w];
	}
	
	public static void main(String[] args) {
		SparseGraph sparseGraph = GraphUtil.generateSparseGraph();
		ShortestPath path = new ShortestPath(sparseGraph, 0);
		System.out.println();
		path.showPath(4);
		System.out.println();
		System.out.println(path.length(4));
	}
}
