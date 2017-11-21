package weightgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Author bingqin
 * @date 2017年10月25日
 * @description 稠密图 用邻接矩阵表示的有权图
 */
public class DenseGraph {
	private int n;//点数
	private int m;//边数
	
	private boolean directed;//是否是有向图
	
	private Edge[][] g; //链接矩阵
	
	public DenseGraph(int n,boolean directed){
		this.n=n;
		this.directed = directed;
		this.m=0;
		g= new Edge[n][n];
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
		//如果有边了，删除并覆盖原来的边
		if(hasEdge(v, w)){
			g[v][w] = null;
			if(!directed) g[w][v] = null;
			m--;
		}
		g[v][w] = new Edge(v, w, weight);
		//如果是无向图
		if(!directed){
			g[w][v] = new Edge(w, v, weight);
		}
		m++;
	}
	
	public boolean hasEdge(int v ,int w){
		assert(v>=0 && v<n);
		assert(w>=0 && w<n);
		return g[v][w] != null;
	}
	public void show(){
		for(int i=0;i<n;i++){
			System.out.println();
			for(int j=0;j<n;j++){
				System.out.printf("%4d",(g[i][j] != null?g[i][j].weight:-1));
			}
		}
	}
}
