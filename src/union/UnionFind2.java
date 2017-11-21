package union;
/**
 * 
 * created by bingqin on 2017年10月23日
 * @description 并查集的快速版本实现。
 *
 */
public class UnionFind2 {
	public int count;
	public int[] parent;
	
	public UnionFind2(int n){
		this.count = n;
		parent = new int[n];
		for(int i=0;i<n;i++){
			parent[i]=i;
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
		parent[pRoot] = qRoot;
	}
}
