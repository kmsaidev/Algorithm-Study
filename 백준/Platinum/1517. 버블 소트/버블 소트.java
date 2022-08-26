import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] nums;
    static int[] tree;
    static HashMap<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < N; i++)
            map.put(sorted[i], i + 1);

        S = 1;
        while(N > S)
            S *= 2;

        tree = new int[S * 2];
        long result = 0;
        for(int i = 0; i < N; i++){
            int after = query(1, S, 1, map.get(nums[i]) + 1, N);
            result += after;
            update(1, S, 1, map.get(nums[i]), 1);
        }

        System.out.println(result);
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if(right < queryLeft || left > queryRight)
            return 0;
        else{
            if(left >= queryLeft && right <= queryRight)
                return tree[node];
            int mid = (left + right) / 2;
            int leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            int rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }

    static void update(int left, int right, int node, int target, int diff){
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
