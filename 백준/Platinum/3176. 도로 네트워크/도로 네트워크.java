import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int A, B, C;
    static int D, E;
    static ArrayList<Node>[] road;
    static int[] depth;
    static boolean[] visited;
    static int[][] sTable;
    static int[][] min;
    static int[][] max;
    static int minRoute, maxRoute;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        road = new ArrayList[N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        sTable = new int[18][N + 1];
        min = new int[18][N + 1];
        max = new int[18][N + 1];
        for (int i = 0; i <= N; i++)
            road[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            road[A].add(new Node(B, C));
            road[B].add(new Node(A, C));
        }
        //깊이와 sTable 초기화
        init();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            minRoute = Integer.MAX_VALUE;
            maxRoute = Integer.MIN_VALUE;
            lca(D, E);
            System.out.println(minRoute + " " + maxRoute);
        }
    }

    static void init() {
        Queue<Node> queue = new LinkedList<>();
        // 여기선 dist를 잠깐 depth로 사용
        queue.add(new Node(1, 1));
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            visited[temp.num] = true;
            depth[temp.num] = temp.dist;
            for (Node node : road[temp.num]) {
                if (!visited[node.num]) {
                    queue.add(new Node(node.num, temp.dist + 1));
                    sTable[0][node.num] = temp.num;
                    min[0][node.num] = node.dist;
                    max[0][node.num] = node.dist;
                }
            }
        }

        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= N; j++) {
                sTable[i][j] = sTable[i - 1][sTable[i - 1][j]];
                min[i][j] = Math.min(min[i - 1][j], min[i - 1][sTable[i - 1][j]]);
                max[i][j] = Math.max(max[i - 1][j], max[i - 1][sTable[i - 1][j]]);
            }
        }
    }

    static void lca(int a, int b) {
        int depthA = depth[a];
        int depthB = depth[b];

        if (depthA != depthB) {
            int diff = depthA - depthB;
            if (diff < 0) {
                int temp = a;
                a = b;
                b = temp;
                diff = -diff;
            }

            for (int i = 17; i >= 0; i--) {
                if ((diff & (1 << i)) > 0) {
                    maxRoute = Math.max(maxRoute, max[i][a]);
                    minRoute = Math.min(minRoute, min[i][a]);
                    a = sTable[i][a];
                }
            }
        }

        //깊이 조정하는 것이 틀렸을 때 오류 출력
        if (depth[a] != depth[b])
            System.out.println("깊이 조정 오류");

        if (a == b) {
            return;
        }
        else {
            for (int i = 17; i >= 0; i--) {
                if (sTable[i][a] != sTable[i][b]) {
                    maxRoute = Math.max(maxRoute, Math.max(max[i][a], max[i][b]));
                    minRoute = Math.min(minRoute, Math.min(min[i][a], min[i][b]));
                    a = sTable[i][a];
                    b = sTable[i][b];
                }
            }
            minRoute = Math.min(minRoute, Math.min(min[0][a], min[0][b]));
            maxRoute = Math.max(maxRoute, Math.max(max[0][a], max[0][b]));
        }
    }
}

class Node{
    int num, dist;

    public Node(int num, int dist) {
        this.num = num;
        this.dist = dist;
    }
}
