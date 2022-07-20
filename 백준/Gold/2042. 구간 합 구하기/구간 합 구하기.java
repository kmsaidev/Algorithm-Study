import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] nums;
    static long[] tree;
    static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long a, b, c;

        nums = new long[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Long.parseLong(st.nextToken());
        }

        S = 1;
        while(S < N){
            S *= 2;
        }
        tree = new long[S * 2];
        tree[0] = 0;
        initBu();
        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if(a == 1){
                long diff = tree[S + (int)b - 1] - c;
                update(1, S, 1, (int)b, -diff);
            } else if(a == 2){
                System.out.println(query(1, S, 1, (int)b, (int)c));
            }
        }
    }

    static void initBu(){
        // Leaf에 값 반영
        for(int i = 0; i < N; i++)
            tree[S + i] = nums[i];
        // 내부노드 채움
        for(int i = S - 1; i > 0; i--)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 연관이 없음 -> 결과에 영향이 없는 값 return
        if(queryRight < left || queryLeft > right)
            return 0;
        // 판단 가능 -> 현재 노드 값 return
        else if(left >= queryLeft && right <= queryRight)
            return tree[node];
        // 판단불가, 자식에게 위임, 자식에서 올라온 합을 return
        else{
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        //연관없음
        if(target < left || target > right)
            return;
        //연관 있음 -> 현재 노드에 diff 반영 -> 자식에게 diff전달
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
