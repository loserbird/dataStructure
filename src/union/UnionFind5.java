package union;
/**
 * 
 * created by bingqin on 2017年10月23日
 * @description 并查集的快速版本实现。路径压缩，在寻找的过程中减少树的层树。优化树的结构
 *
 */
public class UnionFind5 {
	public int count;
	public int[] rank;//rank[i]表示以i为根的集合所表示的数的层次
	public int[] parent;
	
	public UnionFind5(int n){
		this.count = n;
		parent = new int[n];
		rank = new int[n];
		for(int i=0;i<n;i++){
			parent[i]=i;
			rank[i]= 1;
		}
	}
	//寻找p的根
	int find(int p){
		assert(p>=0 && p<=count);
	/*	while(p != parent[p]){
			parent[p] = parent[parent[p]];//路径压缩1
			p = parent[p];
		}
		return p;*/
		
		//路径压缩2，压缩为两层
		if(p != parent[p]){
			parent[p] = find(parent[p]);
		}
		return parent[p];
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
