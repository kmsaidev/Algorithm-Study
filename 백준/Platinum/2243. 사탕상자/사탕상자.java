import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int type = 1000000;
    static int A, B, C;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = 1;

        while(S < type)
            S *= 2;
        tree = new int[S * 2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if(A == 1){
                B = Integer.parseInt(st.nextToken());
                int answer = query(1, S, 1, B);
                System.out.println(answer);
                update(1, S, 1, answer, -1);
            } else if(A == 2){
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                update(1, S, 1, B, C);
            }
        }
    }

    static int query(int left, int right, int node, int value) {
        if(left == right)
            return left;
        else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= value) {
                int leftResult = query(left, mid, node * 2, value);
                return leftResult;
            } else {
                int rightResult = query(mid + 1, right, node * 2 + 1, value - tree[node * 2]);
                return rightResult;
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
