package weightgraph;

import java.util.ArrayList;
import java.util.List;

import heap.GeneralMinHeap;
import union.UnionFind;

public class KruskalMST {
	ArrayList<Edge> mst;//用来储存构成最小生成树的所有边
	
	int mstWeight;//最小生成树的权重
	
	SparseGraph graph;//这里以稀疏图为例
	
	GeneralMinHeap<Edge> minHeap;//用来排序
	
	public KruskalMST(SparseGraph graph){
		this.graph = graph;
		mst = new ArrayList<>(graph.E());
		minHeap = new GeneralMinHeap<Edge>(graph.E());
		 //排序
		 for( int i = 0 ; i < graph.V() ; i ++ ){
			 List<Edge> edges = graph.g.get(i);
			 for(Edge edge : edges ){
				 if(edge.v() < edge.w()) minHeap.insert(edge);
			 }
		 }
		 //用并查集来辅助判断是否有环等操作
		 UnionFind uf = new UnionFind(graph.V());
	        while( !minHeap.isEmpty() && mst.size() < graph.V() - 1 ){

	            Edge e = minHeap.removeMin();
	            //如果有环就跳过
	            if( uf.isConnected( e.v() , e.w() ) )
	                continue;

	            mst.add( e );
	            //在并查集中把e,v连起来
	            uf.unionElements( e.v() , e.w() );
	        }

	        mstWeight = mst.get(0).wt();
	        for( int i = 1 ; i < mst.size() ; i ++ )
	            mstWeight += mst.get(i).wt();
	}
	
	public int getMinWeight(){
		return mstWeight;
	}
	public ArrayList<Edge> getMinGraphEdges(){
		return mst;
	}
}
