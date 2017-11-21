package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GraphUtil {
	//生成一个稠密图
	public static DenseGraph generateDenseGraph(){
		InputStream inputStream = GraphUtil.class.getClassLoader().getResourceAsStream("graph/G1.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String string = "";
		DenseGraph denseGraph = null;
		try {
			string = bufferedReader.readLine();
			denseGraph = new DenseGraph(Integer.parseInt(string), false);
			while((string = bufferedReader.readLine()) != null){
				//System.out.println(string);
				String v = string.split(" ")[0];
				String w = string.split(" ")[1];
				denseGraph.addEdge(Integer.parseInt(v), Integer.parseInt(w));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return denseGraph;
	}
	//生成一个稀疏图
	public static SparseGraph generateSparseGraph(){
		InputStream inputStream = GraphUtil.class.getClassLoader().getResourceAsStream("graph/G2.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String string = "";
		SparseGraph denseGraph = null;
		try {
			string = bufferedReader.readLine();
			denseGraph = new SparseGraph(Integer.parseInt(string), false);
			while((string = bufferedReader.readLine()) != null){
				//System.out.println(string);
				String v = string.split(" ")[0];
				String w = string.split(" ")[1];
				denseGraph.addEdge(Integer.parseInt(v), Integer.parseInt(w));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return denseGraph;
	}
	
	public static void main(String[] args) {
		/*DenseGraph denseGraph = generateDenseGraph();
		denseGraph.show();*/
		//System.out.println();
		SparseGraph sparseGraph = generateSparseGraph();
		sparseGraph.show();
		//sparseGraph.dfs(0);
		sparseGraph.stackdfs(0);
		System.out.println();
		sparseGraph.bfs(0);
	}
}
