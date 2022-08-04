import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] powers, sortedPowers;
    static HashMap<Integer, Integer> map;
    static int[] tree;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new HashMap<>();
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        S = 1;
        while(S < N)
            S *= 2;
        tree = new int[S * 2];
        powers = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            powers[i] = Integer.parseInt(st.nextToken());
        }
        sortedPowers = powers.clone();
        Arrays.sort(sortedPowers);
        for(int i = 0; i < N; i++)
            map.put(sortedPowers[i], i);
        for(int i = 0; i < N; i++)
            powers[i] = map.get(powers[i]);

        for(int i = 0; i < N; i++) {
            sb.append((i + 1) - query(1, S, 1, 1, powers[i]) + "\n");
            update(1, S, 1, powers[i] + 1, 1);
        }
        System.out.println(sb);
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if(right < queryLeft || left > queryRight)
            return 0;
        else{
            if(queryLeft <= left && queryRight >= right){
                return tree[node];
            }else{
                int mid = (left + right) / 2;
                int leftResult = query(left, mid, node * 2, queryLeft, queryRight);
                int rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
                return leftResult + rightResult;
            }
        }
    }

    static void update(int left, int right, int node, int target, int diff){
        if(target > right || target < left)
            return;
        else{
            tree[node] += diff;
            if(left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
