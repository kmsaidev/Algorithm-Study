import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] heights, bucket;
    static boolean isEnd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        heights = new int[9];
        bucket = new int[7];
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            heights[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(heights);
        isEnd = false;
        solution(9, bucket, 7, 7);
    }

    static void solution(int n, int[] bucket, int bucketSize, int k){
        if(isEnd)
            return;
        int sum = 0;
        for(int i = 0; i < bucketSize; i++)
            sum += heights[bucket[i]];
        if(k == 0){
            if(sum == 100) {
                for (int i = 0; i < bucketSize; i++)
                    System.out.println(heights[bucket[i]]);
                isEnd = true;
            }
            return;
        }
        int smallest, lastIndex;

        lastIndex = bucketSize - k - 1;
        if(bucketSize == k)
            smallest = 0;
        else smallest = bucket[lastIndex] + 1;
        for(int i = smallest; i < n; i++){
            bucket[lastIndex + 1] = i;
            solution(n, bucket, bucketSize, k - 1);
        }
    }
}
