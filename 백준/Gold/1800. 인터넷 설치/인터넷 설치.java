import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, P, K;
    static ArrayList<Node>[] graph;
    static int answer = -1;
    static int[] dist;
    static int inf = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];

        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < P; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, weight));
            graph[e].add(new Node(s, weight));
            max = Math.max(max, weight);
        }
        int left = 0;
        int right = max;

        while(left <= right){
            int mid = (left + right) / 2;
            if(dijkstra(mid)){
                right = mid - 1;
                answer = mid;
            } else
                left = mid + 1;
        }
        System.out.println(answer);
    }
    static boolean dijkstra(int mid){
        Arrays.fill(dist, inf);
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        });
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node pop = pq.poll();
            if(pop.weight > dist[pop.num])
                continue;
            for(Node next : graph[pop.num]){
                int weight = next.weight > mid ? 1 : 0;
                if(dist[next.num] > pop.weight + weight){
                    dist[next.num] = pop.weight + weight;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
        return dist[N] <= K;
    }
}

class Node{
    int num, weight;

    public Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}
