import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q, S;
    static int X, Y, A;
    static long B;
    static long[] tree;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        S = 1;
        while(S < N)
            S *= 2;
        tree = new long[S * 2];
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        init();
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Long.parseLong(st.nextToken());
            if(X > Y){
                int temp = X;
                X = Y;
                Y = temp;
            }
            sb.append(query(1, S, 1, X, Y) + "\n");
            update(1, S, 1, A, B - tree[S + A - 1]);
        }
        System.out.println(sb);
    }

    static void init(){
        for(int i = 0; i < N; i++)
            tree[S + i] = nums[i];

        for(int i = S - 1; i >= 1; i--)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        if(right < queryLeft || left > queryRight)
            return 0;
        else{
            if(left >= queryLeft && right <= queryRight)
                return tree[node];
            else{
                int mid = (left + right) / 2;
                long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
                long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
                return leftResult + rightResult;
            }
        }
    }

    static void update(int left, int right, int node, int target, long diff){
        if(target > right || target < left)
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
