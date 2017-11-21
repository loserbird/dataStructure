package weightgraph;

import java.util.ArrayList;
import java.util.List;

import heap.IndexMinHeap;
/**
 * 
 * @Author bingqin
 * @date 2017年10月26日
 * @description 用优化过的Prim算法生成最小生成树
 */
public class PrimMST {
	
	boolean[] marked;//标记是否已纳入生成树的一方
	
	ArrayList<Edge> mst;//用来储存构成最小生成树的所有边
	
	int mstWeight;//最小生成树的权重
	
	IndexMinHeap<Edge> minHeap;//用来储存边的最小索引堆
	
	SparseGraph graph;//这里以稀疏图为例
	
	private Edge[] edgeTo;
	
	// 保证图是连通无向有权图
	public PrimMST(SparseGraph graph){
		this.graph = graph;
		marked = new boolean[graph.V()];
		mst = new ArrayList<>(graph.E());
		minHeap = new IndexMinHeap<>(graph.V());
		edgeTo = new Edge[graph.V()];
		//假设从0节点开始划分
		visit(0);
		while(!minHeap.isEmpty()){
			int v = minHeap.removeMinindex();
			assert( edgeTo[v] != null);
			mst.add(edgeTo[v]);//加入到生成树中
			visit(v);
		}
		mstWeight = mst.get(0).wt();
		for(int i=1;i<mst.size();i++){
			mstWeight += mst.get(i).wt();
		}
	}
	
	//访问v节点
	public void visit(int v){
		assert(!marked[v]);//确保v节点没被纳入最小生成树的一方
		marked[v] = true;
		
		//遍历以v节点为端点的所有边
		List<Edge> vEdges = graph.g.get(v);
		for(int i=0;i<vEdges.size();i++){
			Edge edge = vEdges.get(i);
			int w = edge.other(v);
			if(!marked[w]){ 
				if(edgeTo[w] == null){ //如果不存在则加进去索引所指的数组中
					edgeTo[w] = edge;
					minHeap.insert(w,edge);
				}else if(edge.wt()<edgeTo[w].wt()){
					edgeTo[w] = edge;
					minHeap.change(w, edge);
				}
				
			}
		}
	}
	
	public int getMinWeight(){
		return mstWeight;
	}
	public ArrayList<Edge> getMinGraphEdges(){
		return mst;
	}
}
