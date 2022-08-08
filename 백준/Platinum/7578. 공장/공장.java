import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arrA;
    static HashMap<Integer, Integer> map;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        arrA = new int[N + 1];
        S = 1;
        while(N > S)
            S *= 2;
        tree = new int[S * 2];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            arrA[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, i);
        }
        long result = 0;
        for(int i = 1; i <= N; i++){
            int adj = map.get(arrA[i]);
            int count = query(1, S, 1, adj, N);
            result += count;
            update(1, S, 1, adj, 1);
        }
        System.out.println(result);
    }
    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if(right < queryLeft || left > queryRight)
            return 0;
        else{
            if(left >= queryLeft && right <= queryRight)
                return tree[node];
            else{
                int mid = (left + right) / 2;
                int leftResult = query(left, mid, node * 2, queryLeft, queryRight);
                int rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
                return leftResult + rightResult;
            }
        }
    }

    static void update(int left, int right, int node, int target, int diff){
        if(right < target || left > target)
            return;
        else{
            tree[node] += diff;
            if(left != right){
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
