package shortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import heap.IndexMinHeap;
import weightgraph.Edge;
import weightgraph.SparseGraph;

public class Dijkstra {
	SparseGraph graph;//这里以稀疏图为例
	int s;//源点
	int[] distTo;//最短距离
	boolean[] marked;//标记节点i是否已经找到最短路径
	ArrayList<Edge> from;//找到时是从哪一条边遍历过来的
	IndexMinHeap<Integer> indexMinHeap;
	
	public Dijkstra(SparseGraph graph,int s){
		this.graph = graph;
		this.s=s;
		distTo = new int[graph.V()];
		marked = new boolean[graph.V()];
		from = new ArrayList<>(graph.V());
		indexMinHeap = new IndexMinHeap<>(graph.V());
		
		// start dijkstra
		distTo[s] = 0;
		indexMinHeap.insert(s, distTo[s]);
		marked[s] = true;
		while(!indexMinHeap.isEmpty()){
			int v = indexMinHeap.removeMinindex();
			// distTo[v]就是s到v的最短距离
			marked[v] = true;
			
			//遍历与v的所有相邻的边
			List<Edge> vEdges = graph.g.get(v);
			for(int i=0;i<vEdges.size();i++){
				Edge edge = vEdges.get(i);
				int w = edge.other(v);
				if(!marked[w]){//如果w节点还没被标记为已经找到最短路径
					if(from.get(w)==null || distTo[v] + edge.wt() < distTo[w] ){
						distTo[w] = distTo[v] + edge.wt();
	                    from.set(w,edge);
	                    if(indexMinHeap.contain(w)){
	                    	indexMinHeap.change(w, distTo[w]);
	                    }else{
	                    	indexMinHeap.insert(w,distTo[w]);
	                    }
					}
				}
			}
		}
	}
	
	int shortestPathTo( int w ){
        assert( w >= 0 && w < graph.V() );
        return distTo[w];
    }

    boolean hasPathTo( int w ){
        assert( w >= 0 && w < graph.V() );
        return marked[w];
    }
    //将最短路径的路径加入到list中
    void shortestPath( int w,ArrayList<Edge> list){

        assert( w >= 0 && w < graph.V() );

        Stack<Edge> s = new Stack<>();
        Edge e = from.get(w);
        while( e.v() != this.s ){
            s.push(e);
            e = from.get(e.v());
        }
        s.push(e);

        while( !s.empty() ){
            list.add(s.pop());
        }
    }

    void showPath(int w){

        assert( w >= 0 && w < graph.V() );

        ArrayList<Edge> list = new ArrayList<>();
        shortestPath(w, list);
        for( int i = 0 ; i < list.size() ; i ++ ){
        	System.out.print(list.get(i).v()+"->");
           if(i == list.size()-1) System.out.print(list.get(i).v());
        }
    }
}
