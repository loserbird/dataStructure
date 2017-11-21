package sort;

public class BubbleSort {
	
	static void sort(int[] arr,int n){
		int flag = n;
		while(flag >0){
			int k = flag;
			flag = 0;
			for(int i=1;i<k;i++){
				if(arr[i]<arr[i-1]){
					int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
					flag = i;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{49, 38, 65, 97, 26, 13, 27, 49, 55, 4};
		sort(arr,arr.length);
		for(int a:arr){
			System.out.print(a+",");
		}
	}
	
}
