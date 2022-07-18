import java.util.*;
public class Main {
	static int[][] visited;
	static int R, C;
	static String[] map;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		ArrayList<Point> list = new ArrayList<>();
		Point s = null;
		
		map = new String[R];
		visited = new int[R][C];
		for(int i = 0; i < R; i++)  
			map[i] = sc.next();
		for(int i = 0; i < R; i++) {  
			for(int j = 0; j < C; j++) {
				if(map[i].charAt(j) == '*')
					list.add(new Point(i, j, '*'));
				else if(map[i].charAt(j) == 'S')
					s = new Point(i, j, 'S');
			}
		}
		list.add(s);
		int depth = bfs(list);
		if(depth == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(depth);
	}

	static int bfs(ArrayList<Point> list) {
		int[] move_x = {0, 0, -1, 1};
		int[] move_y = {-1, 1, 0, 0};
		int depth = -1;
		Queue<Point> queue = new LinkedList<>();
		for(Point p : list) {
			visited[p.x][p.y] = 0;
			queue.add(p);
		}
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for(int i = 0; i < 4; i++) {
				int new_x = p.x + move_x[i];
				int new_y = p.y + move_y[i];
				if(0 <= new_x && new_x < R && 0 <= new_y && new_y < C) {
					if(p.type != '*' && map[new_x].charAt(new_y) == 'D') {
						depth = visited[p.x][p.y] + 1;
						break;
					}
					else if(visited[new_x][new_y] == 0 
							&& map[new_x].charAt(new_y) == '.') {
						visited[new_x][new_y] = visited[p.x][p.y] + 1;
						if(p.type == '*')
							queue.add(new Point(new_x, new_y, '*'));
						else
							queue.add(new Point(new_x, new_y, '.'));
					}
				}	
			}
			if(depth != -1)
				break;
		}
		return depth;
	}
}
class Point {
	int x, y;
	char type;
	public Point(int x, int y, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}