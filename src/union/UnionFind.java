package union;
/**
 * 
 * created by bingqin on 2017年10月23日
 * @description 并查集的简单版本实现
 *
 */
public class UnionFind {
	public int count;
	public int[] id;
	
	public UnionFind(int n){
		this.count = n;
		id = new int[n];
		for(int i=0;i<n;i++){
			id[i]=i;
		}
	}
	
	int find(int p){
		assert(p>=0 && p<=count);
		return id[p];
	}
	public boolean isConnected(int p,int q){
		return find(p) == find(q);
	}
	
	public void unionElements(int p,int q){
		int pId = find(p);
		int qId = find(q);
		
		if(pId == qId){
			return;
		}
		for(int i=0;i<count;i++){
			if(id[i] == pId){
				id[i] = qId;
			}
		}
	}
}
