package shortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.text.StyledEditorKit.BoldAction;

import heap.IndexMinHeap;
import weightgraph.Edge;
import weightgraph.SparseGraph;

public class BellmanFord {
	SparseGraph graph;//这里以稀疏图为例
	int s;//源点
	int[] distTo;//最短距离
	ArrayList<Edge> from;//找到时是从哪一条边遍历过来的
	boolean hasNegativeCycle;//是否有负权环
	
	public BellmanFord(SparseGraph graph,int s){
		this.graph = graph;
		this.s=s;
		distTo = new int[graph.V()];
		from = new ArrayList<>(graph.V());
		
		// start dijkstra
		distTo[s] = 0;
		
		for(int pass = 1;pass<graph.V();pass++){
			//Relation松弛操作
			for(int i=0;i<graph.V();i++){
				List<Edge> vEdges = graph.g.get(i);
				for(int j=0;j<vEdges.size();j++){
					 Edge e = vEdges.get(i);
					 if( from.get(e.w()) ==null|| distTo[e.v()] + e.wt() < distTo[e.w()] ){
	                        distTo[e.w()] = distTo[e.v()] + e.wt();
	                        from.set(e.w(),e);
	                 }
				}
			}
		}
		 hasNegativeCycle = detectNegativeCycle();
	}
	//判断是否有负权环
	private boolean detectNegativeCycle(){
		for(int i=0;i<graph.V();i++){
			List<Edge> vEdges = graph.g.get(i);
			for(int j=0;j<vEdges.size();j++){
				 Edge e = vEdges.get(i);
				 if( from.get(e.w()) ==null|| distTo[e.v()] + e.wt() < distTo[e.w()] ){
                        return true;
                 }
			}
		}
		return false;
	}
	
	public boolean negativeCycle(){
	    return hasNegativeCycle;
	}
	
	int shortestPathTo( int w ){
        assert( w >= 0 && w < graph.V() );
        return distTo[w];
    }

    boolean hasPathTo( int w ){
        assert( w >= 0 && w < graph.V() );
        return from.get(w)!=null;
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
