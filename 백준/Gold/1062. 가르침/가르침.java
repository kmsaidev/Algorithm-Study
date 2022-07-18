import java.util.*;
public class Main {
	static boolean[] visited;
	static String[] words;
	static int selectedCount = 0;
	static int max = 0;
	static int n, k;
	static int[] def = {'a' - 'a', 'c' - 'a','n' - 'a', 't' - 'a', 'i' - 'a'};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		
		if(k < 5)
			System.out.println("0");
		else {
			words = new String[n];
			for(int i = 0; i < n; i++)
				words[i] = sc.next().replaceAll("[antic]", "");
			visited = new boolean[26];
			
			init();
			selectedCount = 5;
			max = countWords();
			for(int i = 0; i < 26; i++)
				if(!visited[i])
					dfs(i);
			System.out.println(max);
		}
	}

	
	static void init() {
		for(int i = 0; i < visited.length; i++)
			visited[i] = false;
		
		for(int i = 0; i < def.length; i++)
			visited[def[i]] = true;
		
	}
	
	static void dfs(int index) {
		//1. 체크인
		visited[index] = true;
		selectedCount++;
		//2. 목적지인가?
		if(selectedCount == k) {
			max = Math.max(max, countWords());
		}
		//3. 연결된 곳을 순회
		else {
			for(int i = (index + 1); i < 26; i++) {
//			    4. 갈 수 있는가? 
				if(!visited[i]) {
//			        5. 간다.
					dfs(i);
				}
			}
		}
		//6. 체크아웃
		visited[index] = false;
		selectedCount--;
	}
	
	static int countWords() {
		boolean isPossible = true;
		int count = 0;
		for(int i = 0; i < words.length; i++) {
			for(int j = 0; j < words[i].length(); j++) {
				if(!visited[words[i].charAt(j) - 'a']) {
					isPossible = false;
					break;
				}
			}
			if(isPossible)
				count++;
			else
				isPossible = true;
		}
		return count;
	}
}
