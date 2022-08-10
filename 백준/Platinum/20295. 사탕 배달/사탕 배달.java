import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, U, V, M, store, type;
    static int[] candyType, depth, bitMask;
    static boolean[] visited;
    static ArrayList<Integer>[] tree;
    static int[][] sTable, eTable;
    static int start = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        candyType = new int[N + 1];
        depth = new int[N + 1];
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        sTable = new int[18][N + 1];
        eTable = new int[18][N + 1];
        bitMask = new int[N + 1];
        for(int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            candyType[i] = Integer.parseInt(st.nextToken());
            bitMask[i] = 1 << (candyType[i] - 1);
        }

        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            tree[U].add(V);
            tree[V].add(U);
        }

        boolean isFirst = true;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        boolean isPossible;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            store = Integer.parseInt(st.nextToken());
            type = Integer.parseInt(st.nextToken());
            if(isFirst) {
                isFirst = false;
                init(type);
                isPossible = start != -1;
            } else
                isPossible = lca(start, store, type);

            if(isPossible)
                System.out.println("PLAY");
            else
                System.out.println("CRY");
            start = store;
        }
    }

    static void init(int type){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1));

        while(!queue.isEmpty()){
            Node pop = queue.poll();
            depth[pop.num] = pop.depth;
            visited[pop.num] = true;
            if(candyType[pop.num] == type)
                start = pop.num;
            for(int next : tree[pop.num]){
                if(!visited[next]){
                    queue.add(new Node(next, pop.depth + 1));
                    sTable[0][next] = pop.num;
                    eTable[0][next] = bitMask[pop.num];
                }
            }
        }

        for(int i = 1; i < 18; i++) {
            for (int j = 1; j <= N; j++) {
                sTable[i][j] = sTable[i - 1][sTable[i - 1][j]];
                eTable[i][j] = eTable[i - 1][j] | eTable[i - 1][sTable[i - 1][j]];
            }
        }
    }

    static boolean lca(int a, int b, int type){
        int isIn = bitMask[a] | bitMask[b];
        int depthA = depth[a];
        int depthB = depth[b];
        int diff = depthA - depthB;
        if(depthA != depthB){
            if(depthA < depthB){
                int temp = a;
                a = b;
                b = temp;
                diff = -diff;
            }
            for(int i = 17; i >= 0; i--){
                if((diff & (1 << i)) > 0){
                    isIn |= eTable[i][a];
                    a = sTable[i][a];
                }
            }
        }
        if(a == b) {
            if((isIn & (1 << (type - 1))) > 0)
                return true;
            else return false;
        }
        for(int i = 16; i >= 0; i--){
            if(sTable[i][a] != sTable[i][b]){
                isIn |= eTable[i][a];
                isIn |= eTable[i][b];
                a = sTable[i][a];
                b = sTable[i][b];
            }
        }
        isIn |= eTable[0][a];
        isIn |= eTable[0][b];
        if((isIn & (1 << (type - 1))) > 0)
            return true;
        else return false;
    }
}

class Node{
    int num, depth;

    public Node(int num, int depth) {
        this.num = num;
        this.depth = depth;
    }
}
