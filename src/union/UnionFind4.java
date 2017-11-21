package union;
/**
 * 
 * created by bingqin on 2017年10月23日
 * @description 并查集的快速版本实现。
 *
 */
public class UnionFind4 {
	public int count;
	public int[] rank;//rank[i]表示以i为根的集合所表示的数的层次
	public int[] parent;
	
	public UnionFind4(int n){
		this.count = n;
		parent = new int[n];
		rank = new int[n];
		for(int i=0;i<n;i++){
			parent[i]=i;
			rank[i]= 1;
		}
	}
	
	int find(int p){
		assert(p>=0 && p<=count);
		while(p != parent[p]){
			p = parent[p];
		}
		return p;
	}
	boolean isConnected(int p,int q){
		return find(p) == find(q);
	}
	
	public void unionElements(int p,int q){
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot){
			return;
		}
		if(rank[pRoot] < rank[qRoot]){
			parent[pRoot]=qRoot;
		}else if(rank[qRoot] < rank[pRoot]){
			parent[qRoot]=pRoot;
		}else{
			parent[pRoot]=qRoot;
			rank[qRoot] ++;
		}
	}
}
