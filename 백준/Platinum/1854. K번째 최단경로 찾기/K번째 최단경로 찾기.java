import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E ,K;
    static int S, A, B, W;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for(int i = 0; i <= V; i++)
            graph[i] = new ArrayList<>();

        S = 1;
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, W));
        }

        dijkstraKth();
    }

    static void dijkstraKth(){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.distance - o2.distance;
            }
        });

        PriorityQueue<Integer>[] maxHeap = new PriorityQueue[V + 1];
        for(int i = 0; i <= V; i++){
            maxHeap[i] = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }

        pq.add(new Node(S, 0));
        maxHeap[S].add(0);
        while(!pq.isEmpty()){
            Node node = pq.poll();

            for(Node adj : graph[node.n]){
                int temp = node.distance + adj.distance;
                if(maxHeap[adj.n].size() < K){
                    maxHeap[adj.n].add(temp);
                    pq.add(new Node(adj.n, temp));
                } else if(temp < maxHeap[adj.n].peek()){
                    maxHeap[adj.n].poll();
                    maxHeap[adj.n].add(temp);
                    pq.add(new Node(adj.n, temp));
                }
            }
        }

        for(int i = 1; i <= V; i++){
            if(maxHeap[i].size() == K)
                System.out.println(maxHeap[i].peek());
            else System.out.println("-1");
        }
    }
}

class Node{
    int n, distance;

    public Node(int n, int distance) {
        this.n = n;
        this.distance = distance;
    }
}

