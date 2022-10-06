import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while(true){
            N = Integer.parseInt(st.nextToken());
            if(N == 0)
                break;

            nums = new int[N];
            for(int i = 0; i < N; i++)
                nums[i] = Integer.parseInt(st.nextToken());

            int[] bucket = new int[6];
            solution(N, bucket, 6, 6);
            System.out.println();
            st = new StringTokenizer(br.readLine());
        }
    }

    static void solution(int n, int[] bucket, int bucketSize, int k){
        if(k == 0){
            for(int i = 0; i < bucketSize; i++)
                System.out.print(nums[bucket[i]] + " ");
            System.out.println();
            return;
        }
        int lastIndex = bucketSize - k - 1;
        int smallest;
        if(bucketSize == k)
            smallest = 0;
        else
            smallest = bucket[lastIndex] + 1;

        for(int item = smallest; item < n; item++){
            bucket[lastIndex + 1] = item;
            solution(n, bucket, bucketSize, k - 1);
        }

    }
}
