package weightgraph;
/**
 * 
 * @Author bingqin
 * @date 2017年10月26日
 * @description 有权图的边
 */
public class Edge implements Comparable<Edge>{
	int a,b;
	int weight;
	
	public Edge(int a,int b,int weight){
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	public int v(){ return a;}

    public int w(){ return b;}
    
    public int wt(){
    	return weight;
    }
    
    public int other(int x){
        assert( x == a || x == b );
        return x == a ? b : a;
    }

	@Override
	public int compareTo(Edge o) {
		if(this.wt()-o.wt()>0){
			return 1;
		}else if(this.wt() == o.wt()){
			return 0;
		}else{
			return -1;
		}
	}
    
    
}
