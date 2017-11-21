package shortestPath;

public class Main {
	
	//Dijkstra:算法(不支持负权图）
	//逐渐遍历节点
	//把当前节点所有邻边都访问一遍，选择最短的那条，再访问这条边的另外一个点，重复之前的行为。
	//如果path[v]>path[w]+e.weight,e是边（v,w),则更新path[v]=path[w]+e.weight
	//直到访问所有的点，循环结束
	
	//Bellman-Ford单源最短路径算法：（有向图）
	//如果一个图没有负权环，从一点到令外一点的最短路径，最多经过所有的V个
	//顶点，有V-1条边
	//否则，存在顶点经过了两次，即存在负权环
	
	//利用拓扑排序求最短路径算法（有向五环图）
	
	//Floyed算法
	
	
}
