package weightgraph;

import java.util.ArrayList;
import java.util.List;

import heap.GeneralMinHeap;
import heap.MinHeap;
/**
 * 
 * @Author bingqin
 * @date 2017年10月26日
 * @description 用Prim算法生成最小生成树
 */
public class LazyPrimMST {
	
	boolean[] marked;//标记是否已纳入生成树的一方
	
	ArrayList<Edge> mst;//用来储存构成最小生成树的所有边
	
	int mstWeight;//最小生成树的权重
	
	GeneralMinHeap<Edge> minHeap;//用来储存边的最小堆
	
	SparseGraph graph;//这里以稀疏图为例
	
	// 保证图是连通无向有权图
	public LazyPrimMST(SparseGraph graph){
		this.graph = graph;
		marked = new boolean[graph.V()];
		mst = new ArrayList<>(graph.E());
		minHeap = new GeneralMinHeap<Edge>(graph.E());
		//假设从0节点开始划分
		visit(0);
		while(!minHeap.isEmpty()){
			Edge edge = minHeap.removeMin();
			if(marked[edge.v()] && marked[edge.w()]){ //如果这条边两边的端点都已经加入到生成树中，则跳过
				continue;
			}
			mst.add(edge);//加入到生成树中
			if(!marked[edge.v()]){
				visit(edge.v());
			}
			if(!marked[edge.w()]){
				visit(edge.w());
			}
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
			if(!marked[edge.other(v)]){ //如果这条边的另一个端点没有加入到生成树中，则把边加入到最小堆中
				minHeap.insert(edge);
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
