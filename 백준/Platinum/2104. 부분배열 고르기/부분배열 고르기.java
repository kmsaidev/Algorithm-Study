import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] nums;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = 1;

        while(S < N)
            S *= 2;
        nums = new int[N];
        tree = new long[S * 2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        init();

        System.out.println(solution(0, N - 1));
    }

    static long solution(int left, int right){
        if(left == right)
            return nums[left] * (long)nums[left];

        int mid = (left + right) / 2;

        long leftResult = solution(left, mid);
        long rightResult = solution(mid + 1, right);

        long max = Math.max(leftResult, rightResult);

        return Math.max(max, getMid(left, mid, right));
    }

    static long getMid(int start, int mid, int end){
        int moveL = mid - 1;
        int moveR = mid + 1;
        int areaL = mid;
        int areaR = mid;
        int min = nums[mid];
        long result = min * min;
        while(moveL >= start && moveR <= end){
            if(nums[moveL] > nums[moveR]){
                areaL = moveL;
                min = Math.min(nums[areaL], min);
                result = Math.max(result, min * query(1, S, 1, areaL + 1, areaR + 1));
                moveL--;
            } else {
                areaR = moveR;
                min = Math.min(nums[areaR], min);
                result = Math.max(result, min * query(1, S, 1, areaL + 1, areaR + 1));
                moveR++;
            }
        }
        while(moveL >= start){
            areaL = moveL;
            min = Math.min(min, nums[areaL]);
            result = Math.max(result, min * query(1, S, 1, areaL + 1, areaR + 1));
            moveL--;
        }
        while(moveR <= end){
            areaR = moveR;
            min = Math.min(min, nums[areaR]);
            result = Math.max(result, min * query(1, S, 1, areaL + 1, areaR + 1));
            moveR++;
        }

        return result;
    }

    static void init(){
        for(int i = 0; i < N; i++)
            tree[S + i] = nums[i];

        for(int i = S - 1; i >= 1; i--)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        if(queryLeft > right || queryRight < left)
            return 0;
        else{
            if(queryLeft <= left && queryRight >= right)
                return tree[node];
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }
}


