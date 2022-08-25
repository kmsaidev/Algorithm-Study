import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static char[][] map;
    static Point start, end;
    static int[] move_row = {-1, 1, 0, 0};
    static int[] move_col = {0, 0, -1, 1};
    static int[][] dist;
    static int inf = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        dist = new int[H][W];
        ArrayList<Point> c = new ArrayList<>();
        for(int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'C')
                    c.add(new Point(i, j, 0, 0));
                dist[i][j] = inf;
            }
        }
        start = c.get(0);
        end = c.get(1);

        dijkstra();
        System.out.println(dist[end.row][end.col]);
    }

    static void dijkstra(){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        dist[start.row][start.col] = 0;
        for(int i = 0; i < 4; i++){
            int new_row = start.row + move_row[i];
            int new_col = start.col + move_col[i];
            if(0 <= new_row && new_row < H && 0 <= new_col && new_col < W
                    && map[new_row][new_col] != '*') {
                pq.add(new Point(new_row, new_col, 0, i));
                dist[new_row][new_col] = 0;
            }
        }

        while(!pq.isEmpty()){
            Point p = pq.poll();

            if(dist[p.row][p.col] < p.cost)
                continue;
            for(int i = 0; i < 4; i++){
                int new_row = p.row + move_row[i];
                int new_col = p.col + move_col[i];
                int cost = i == p.direction ? dist[p.row][p.col] : dist[p.row][p.col] + 1;
                if(0 <= new_row && new_row < H && 0 <= new_col && new_col < W
                        && map[new_row][new_col] != '*' && dist[new_row][new_col] >= cost) {
                    dist[new_row][new_col] = cost;
                    pq.add(new Point(new_row, new_col, cost, i));
                }
            }
        }
    }
}

class Point implements Comparable<Point> {
    int row, col, cost, direction;

    public Point(int row, int col, int cost, int direction) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.direction = direction;
    }

    public int compareTo(Point other){
        return this.cost - other.cost;
    }
}
