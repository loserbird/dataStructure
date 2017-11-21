package graph;

import java.util.Stack;

/**
 * 
 * @Author bingqin
 * @date 2017年10月25日
 * @description 求源点s到图中其它节点的路径
 */
public class Path {
	private SparseGraph graph;
	
	private int[] from;//from[i]表示i节点是从哪个遍历来的
	
	private boolean[] visited;
	
	private int s;//原点s
	
	public Path(SparseGraph graph,int s){
		this.graph = graph;
		from = new int[graph.V()];
		for(int i=0;i<graph.V();i++){
			from[i] = -1;
		}
		visited = new boolean[graph.V()];
		this.s = s;
	}
	
	public void dfs(int i){
		visited[i] = true;
		for(int j=0;j<graph.g.get(i).size();j++){
			int v = graph.g.get(i).get(j);
			if(!visited[v]){
				from[v] = i;
				dfs(v);
			}
		}
	}
	//s到w有没有路径
	public boolean hasEdge(int w){
		assert(w>=0 && w<graph.V());
		dfs(s);
		return visited[w];//如果为true则表明从s进行深度遍历的时候有遍历过他
	}
	//s到w的路径
	public void showPath(int w){
		if(!hasEdge(w)){
			System.out.println("no path");
		}
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
	
	public static void main(String[] args) {
		SparseGraph sparseGraph = GraphUtil.generateSparseGraph();
		Path path = new Path(sparseGraph, 0);
		path.showPath(7);
	}
}
