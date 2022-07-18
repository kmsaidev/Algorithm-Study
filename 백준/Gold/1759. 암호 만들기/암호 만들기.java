import java.util.*;
public class Main {
	static int L, C;
	static char[] c;
	static boolean[] visited;
	static int selectedCount = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		
		c = new char[C];
		visited = new boolean[C];
		init();
		for(int i = 0; i < C; i++)
			c[i] = sc.next().charAt(0);
		
		Arrays.sort(c);
		for(int i = 0; i < c.length; i++)
			if(!visited[i])
				dfs(i);
	}
	
	static void init() {
		for(int i = 0; i < C; i++)
			visited[i] = false;
	}
	
	static void dfs(int index) {
		//1. 체크인
		visited[index] = true;
		selectedCount++;
		//2. 목적지인가?
		if(selectedCount == L) {
			printPwd();
		}
		//3. 연결된 곳을 순회
		else {
			for(int i = index + 1; i < c.length; i++)
				//4. 갈 수 있는가?
				if(!visited[i])
					//5. 간다.
					dfs(i);
		}
		//6. 체크아웃
		visited[index] = false;
		selectedCount--;
	}
	
	static void printPwd() {
		String pwd = "";
		boolean isPossible = false;
		int cnt = 0;
		for(int i = 0; i < C; i++) {
			if(visited[i]) {
				pwd += c[i];
				if(c[i] == 'a' || c[i] == 'e' || c[i] == 'i' ||
						c[i] == 'o' || c[i] == 'u')
					isPossible = true;
				else
					cnt++;
			}
		}
		if(isPossible && cnt > 1)
			System.out.println(pwd);
	}
}
