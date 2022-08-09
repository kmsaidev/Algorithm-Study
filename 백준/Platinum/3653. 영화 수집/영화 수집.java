import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int N, M, S;
    static int[] tree;
    static int[] idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = 1;
        while(S < 200000)
            S *= 2;
        tree = new int[S * 2];
        idx = new int[100001];
        T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            init();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                System.out.print(query(1, S, 1, 1, idx[num] - 1) + " ");
                update(1, S, 1, idx[num], -1);
                idx[num] = M - j;
                update(1, S, 1, idx[num], 1);
            }
            System.out.println();
            Arrays.fill(tree, 0);
        }
    }
    static void init(){
        for(int i = 0; i < N; i++) {
            tree[S + M + i] = 1;
            idx[i + 1] = M + i + 1;
        }

        for(int i = S - 1; i >= 1; i--)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if(queryLeft > right || queryRight < left)
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
