package union;
/**
 * 
 * created by bingqin on 2017年10月23日
 * @description 并查集的快速版本实现。让根的子节点多的指向少的
 *
 */
public class UnionFind3 {
	public int count;
	public int[] size;//size[i]表示以i为根的节点数目
	public int[] parent;
	
	public UnionFind3(int n){
		this.count = n;
		parent = new int[n];
		size = new int[n];
		for(int i=0;i<n;i++){
			parent[i]=i;
			size[i]= 1;
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
		if(size[pRoot] > size[qRoot]){
			parent[pRoot]=qRoot;
			size[qRoot] += size[pRoot];
		}else if(size[qRoot] > size[pRoot]){
			parent[qRoot]=pRoot;
			size[pRoot] += size[qRoot];
		}
	}
}
