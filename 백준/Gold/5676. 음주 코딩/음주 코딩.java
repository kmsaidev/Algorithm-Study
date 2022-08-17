import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, S;
    static int[] nums;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nums = new int[100000];
        tree = new long[131072 * 2];
        while(true) {
            String line = br.readLine();
            if(line == null)
                break;
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            S = 1;
            while (N > S)
                S *= 2;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                if(num > 0)
                    nums[i] = 1;
                else if(num < 0)
                    nums[i] = -1;
                else
                    nums[i] = 0;
            }

            init();
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(op == 'C') {
                    if(b > 0)
                        b = 1;
                    else if(b < 0)
                        b = -1;
                    else
                        b = 0;
                    if(tree[S + a - 1] != 0)
                        update(1, S, 1, a, b / tree[S + a - 1]);
                    else
                        update_bu(a, b);
                } else if(op == 'P') {
                    long answer = query(1, S, 1, a, b);
                    if(answer > 0)
                        System.out.print('+');
                    else if(answer < 0)
                        System.out.print('-');
                    else
                        System.out.print(answer);
                }
            }
            System.out.println();
        }
    }

    static void init(){
        for(int i = 0; i < N; i++)
            tree[S + i] = nums[i];

        for(int i = S - 1; i >= 1; i--)
            tree[i] = tree[i * 2] * tree[i * 2 + 1];
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        if(queryLeft > right || queryRight < left)
            return 1;
        else{
            if(queryLeft <= left && queryRight >= right)
                return tree[node];
            else{
                int mid = (left + right) / 2;
                long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
                long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
                return leftResult * rightResult;
            }
        }
    }

    static void update(int left, int right, int node, int target, long diff){
        if(target > right || target < left)
            return;
        else{
            tree[node] *= diff;
            if(left != right){
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    static void update_bu(int target, long diff){
        int node = S + target - 1;
        tree[node] += diff;
        node /= 2;
        while(node > 0) {
            tree[node] = tree[node * 2] * tree[node * 2 + 1];
            node /= 2;
        }
    }
}
