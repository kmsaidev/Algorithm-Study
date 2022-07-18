import java.util.*;
public class Main {
	static int N;
	static boolean[] visited;
	static int[] order;
	static int result = 0;
	static int selectedCount = 0;
 	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[N];
		order = new int[N];
		for(int i = 0; i < N; i++) { 
			visited[i] = false;
			order[i] = -1;
		}
		
		for(int i = 0; i < N; i++) 
			dfs(i);
		System.out.println(result);
	}
 	static void dfs(int index) {
 		visited[index] = true;
 		order[selectedCount] = index;
 		selectedCount++;
 		if(selectedCount == N) {
 			result++;
 		} else {
 			for(int i = 0; i < N; i++) {
 				boolean isPossible = true;
 				if(!visited[i]) {
 					for(int j = 0; j < selectedCount; j++) {
 						if(Math.abs(selectedCount - j) - Math.abs(i - order[j]) == 0) {
 							isPossible = false;
 							break;
 						}
 					}
 					if(isPossible)
 						dfs(i);		
 				}
 			}
 		}
 		visited[index] = false;
 		selectedCount--;
 	}
}
